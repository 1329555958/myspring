package com.wch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author weichunhe
 *         Created on 2016/7/29.
 */
@RequestMapping("/")
@Controller
public class Demo {
    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }
}
