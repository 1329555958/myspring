package com.wch;

import com.wch.interceptor.MyInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by weichunhe on 2016/3/14.
 */
@SpringBootApplication
//@PropertySource("file:/opt/pay/config/matrix/matrix-fmall/application.properties")
//@ImportResource(value = { "classpath:applicationContext.xml" })
@RestController
public class WebApp extends WebMvcConfigurerAdapter {
    //判断是否可以从post中直接获取数据
    // formData 和 requestPayload的区别
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String home(@RequestParam String name) {
        return "Hello " + (name == null ? "world" : name);

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApp.class, args);
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyInterceptor());
//        super.addInterceptors(registry);
//    }

//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/jsp/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
}
