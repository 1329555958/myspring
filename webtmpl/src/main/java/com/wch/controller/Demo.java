package com.wch.controller;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author weichunhe
 *         Created on 2016/7/29.
 */
@RequestMapping("/")
@RestController
public class Demo {
    @Autowired
    private ApplicationContext context;

    private LocalVariableTableParameterNameDiscoverer u =
            new LocalVariableTableParameterNameDiscoverer();

    /**
     * 自定义的URL映射类型
     */
    private static final String CUSTOM_URL_MAPPING_TYPE = "requestMappingHandlerMapping";

    @RequestMapping("/demo/{name}")
    public String demo(@PathVariable String name, @RequestParam int i) {
        return "demo";
    }

    @RequestMapping("/urls")
    public Object mappings() {
        Map<String, List<Map>> result = new LinkedHashMap<String, List<Map>>();
        extractMethodMappings(this.context, result);
        return result;
    }
    @RequestMapping("/upload")
    public String upload(HttpServletRequest req) throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
        MultipartFile file = null;  // NOSONAR
        file = multipartRequest.getFile("file");// 获取上传文件名

        Path path = Paths.get("/tmp/" + file.getOriginalFilename());
        path.toFile().createNewFile();
       file.transferTo(path.toFile());

        return "success";
    }

    protected void extractMethodMappings(ApplicationContext applicationContext,
                                         Map<String, List<Map>> result) {
        if (applicationContext != null) {
            for (Map.Entry<String, AbstractHandlerMethodMapping> bean : applicationContext
                    .getBeansOfType(AbstractHandlerMethodMapping.class).entrySet()) {
                @SuppressWarnings("unchecked")
                Map<?, HandlerMethod> methods = bean.getValue().getHandlerMethods();
                if (!CUSTOM_URL_MAPPING_TYPE.equals(bean.getKey())) {
                    continue;
                }
                for (Map.Entry<?, HandlerMethod> method : methods.entrySet()) {
                    RequestMappingInfo info = (RequestMappingInfo) method.getKey();
                    Map<Map, Map> methodMap = new HashedMap();
                    Map<String, Object> key = new HashedMap();
                    key.put("name", method.getValue().toString());
                    key.put("urls", info.getPatternsCondition().getPatterns());
                    Map<String, Object> value = new LinkedHashMap<String, Object>();
                    List<String> paramNames = Arrays.asList(u.getParameterNames(method.getValue().getMethod()));
                    List<Class> paramTypes = Arrays.asList(method.getValue().getMethod().getParameterTypes());
                    for (int i = 0; i < paramNames.size(); i++) {
                        value.put(paramNames.get(i), paramTypes.get(i));
                    }
                    methodMap.put(key, value);
                    addMethod(result, method.getValue().getBeanType(), methodMap);
                }
            }
        }
    }

    /**
     * 按照Controller归类,Controller 类型作为key，value是map(方法描述作为key,参数信息作为value)
     *
     * @param result
     * @param beanType
     * @param method
     */
    private void addMethod(Map<String, List<Map>> result, Class beanType, Map method) {
        List<Map> methods = result.get(beanType.getName());
        if (methods == null) {
            methods = new ArrayList<Map>();
            result.put(beanType.getName(), methods);
        }
        methods.add(method);
    }
}
