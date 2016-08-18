package com.wch.jsonrpc.client;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.wch.jsonrpc.rpcimpl.UserServiceImpl;
import com.wch.jsonrpc.server.UserService;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.wch.util.JSONUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by weichunhe on 2016/5/23.
 */
public class Client {

    public static void main(String[] args) {
//        main1();
//        main2();
        reflectMethods();
//        try {
//            getParamNames();
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public static void main1() {
        JsonRpcHttpClient client = null;
        try {
            client = new JsonRpcHttpClient(new URL("http://localhost:10010/jsonrpc/com/wch/jsonrpc/server/UserService"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        UserService service = ProxyUtil.createClientProxy(client.getClass().getClassLoader(), UserService.class, client);
        System.out.println(service.findUserByIdAndName("1", "wch"));
    }

    public static void main2() {
        JsonRpcHttpClient client = null;
        try {
            client = new JsonRpcHttpClient(new URL("http://localhost:10010/jsonrpc/com/wch/jsonrpc/server/UserService"));
            System.out.println(client.invoke("findUserByIdAndName", new Object[]{"1", "2"}, String.class));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void reflectMethods() {
        Method[] methods = UserService.class.getDeclaredMethods();
        for (Method method : methods) {
            LocalVariableTableParameterNameDiscoverer u =
                    new LocalVariableTableParameterNameDiscoverer();

            System.out.println(method.getName() + ":" + JSONUtil.toJson(u.getParameterNames(method)));
            Parameter[] parameters = method.getParameters();
            Class[] params = method.getParameterTypes();
            for (Parameter param : parameters) {
                try {
                    System.out.println(param.getName());
                    System.out.println(JSONUtil.toJson(param.getType().newInstance()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void getParamNames() throws NotFoundException {

        Method[] methods = UserServiceImpl.class.getDeclaredMethods();
        Class clazz = methods[0].getDeclaringClass();
        String methodName = methods[0].getName();
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(new ClassClassPath(clazz));
        CtClass cc = pool.get(clazz.getName());
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr =
                (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            System.out.println("params is null");
        }
        String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++)
            paramNames[i] = attr.variableName(i + pos);
        for (int i = 0; i < paramNames.length; i++) {
            System.out.println(paramNames[i]);
        }
    }
}
