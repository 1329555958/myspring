package org.wch.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.util.HTTPRequestUtils;
import org.apache.http.HttpStatus;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by weichunhe on 2016/7/6.
 */
@Component
public class PostFilter extends ZuulFilter {
    /**
     * 三个阶段，pre routing post
     *
     * @return
     */
    @Override
    public String filterType() {
        return "post";
    }

    /**
     * 执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否进行过滤
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        // 根据逻辑判断是否需要执行此过滤
        return true;
    }

    int count = 0;

    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();
        System.out.println("error:" + context.get("error.message") + ":" + context.get("error.exception"));
        if (context.getResponseStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR) {
            try {
                context.getRequest().getRequestDispatcher(context.getRequest().getRequestURI()).forward(context.getRequest(), context.getResponse());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
