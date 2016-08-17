package org.wch.zuul.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.wch.util.JSONUtil;
import org.wch.zuul.ZuulConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class ErrCtrl implements ErrorController {
    private static final String PATH = "/error";

    @Autowired
    private ErrorAttributes errorAttributes;

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = PATH)
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> attributes = getErrorAttributes(request, false);

        if (ZuulConstants.EXCEPTION_NAME.equals(attributes.get("exception")) && ZuulConstants.TIMEOUT_ERROR_MESSAGE.equals(attributes.get("message"))) {
            Object url = request.getAttribute(ZuulConstants.ZUUL_REQUEST_KEY);
//            if (!StringUtils.isEmpty(url)) {
//                try {
//                    request.getRequestDispatcher((String) url).forward(request, response);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }

        }
        System.out.println(request.getAttribute("TIMEOUT") + ":" + JSONUtil.toJson(attributes));
//        if (HttpRequestUtils.isAjax(request)) {
        return new ModelAndView(new MappingJackson2JsonView(), attributes);
//        }


//        return new ModelAndView("/err", "data", attributes);
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request,
                                                   boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }

}
