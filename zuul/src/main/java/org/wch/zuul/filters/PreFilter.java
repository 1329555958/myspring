package org.wch.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.util.HTTPRequestUtils;
import org.springframework.stereotype.Component;
import org.wch.util.JSONUtil;
import org.wch.zuul.ZuulConstants;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by weichunhe on 2016/7/6.
 */
@Component
public class PreFilter extends ZuulFilter {
    /**
     * 三个阶段，pre routing post
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
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

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        context.getRequest().setAttribute(ZuulConstants.ZUUL_REQUEST_KEY, context.getRequest().getRequestURI());
        System.out.println("pre-url:" + context.getRequest().getAttribute(ZuulConstants.ZUUL_REQUEST_KEY));
        return null;
    }
}
