package org.wch.java8.segment;

/**
 * @author weichunhe
 * Created on 2016/11/4.
 * @version 1.0
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CachingEnumResolver {
    //单态实例　一切问题皆由此行引起
    private static final CachingEnumResolver SINGLE_ENUM_RESOLVER = new
            CachingEnumResolver();
    /*MSGCODE->Category内存索引*/
    private static Map CODE_MAP_CACHE;

    //<clinit>()方法是由编译器自动收集类中的所有类变量的赋值动作和静态语句块（static）中的语句合并产生的，编译器收集的顺序是由语句在源文件中出现的顺序所决定的，
    // 静态语句块只能访问到定义在静态语句块之前的变量，而在他之后的变量，
    // 在前面的静态语句块中可以赋值但不能访问
    static {
        System.out.println("static");
        CODE_MAP_CACHE = new HashMap();
        //为了说明问题,我在这里初始化一条数据
        CODE_MAP_CACHE.put("0", "北京市");
    }

    //private, for single instance
    private CachingEnumResolver() {
        System.out.println("init");
        //初始化加载数据  引起问题，该方法也要负点责任
        initEnums();
    }

    /**
     * 初始化所有的枚举类型
     */
    public static void initEnums() {
        // ~~~~~~~~~问题从这里开始暴露 ~~~~~~~~~~~//
        if (null == CODE_MAP_CACHE) {
            System.out.println("CODE_MAP_CACHE为空,问题在这里开始暴露.");
            CODE_MAP_CACHE = new HashMap();
        }
        CODE_MAP_CACHE.put("1", "北京市");
        CODE_MAP_CACHE.put("2", "云南省");

        //..... other code...
    }

    public Map getCache() {
        return Collections.unmodifiableMap(CODE_MAP_CACHE);
    }

    /**
     * 获取单态实例
     *
     * @return
     */
    public static CachingEnumResolver getInstance() {
        return SINGLE_ENUM_RESOLVER;
    }

    public static void main(String[] args) {
        System.out.println(CachingEnumResolver.getInstance().getCache());
    }
}