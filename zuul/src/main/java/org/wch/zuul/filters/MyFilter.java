package org.wch.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.util.HTTPRequestUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by weichunhe on 2016/7/6.
 */
@Component
public class MyFilter extends ZuulFilter {
    /**
     * 三个阶段，pre routing post
     *
     * @return
     */
    @Override
    public String filterType() {
        System.out.println("111111111111111");
        return "pre";
    }

    /**
     * 执行顺序
     *
     * @return
     */
    @Override
    public int filterOrder() {
        System.out.println("22222222222222");
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
        System.out.println("333333333333");
        return true;
    }

    int count = 0;

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        System.out.println("queryParam=" + HTTPRequestUtils.getInstance().getQueryParams());
        System.out.println(HTTPRequestUtils.getInstance().getRequestHeaderMap());
        context.setThrowable(new RuntimeException("count error"));
        context.setResponseStatusCode(HttpServletResponse.SC_NOT_FOUND);
        context.setResponseBody("count=" + count);


//        context.setSendZuulResponse(false);
//        if (count++ % 2 == 1) {
//            return "error count %2 == 1";
//        }
//        System.out.println(RequestContext.getCurrentContext().getRequest().getQueryString());
//        System.out.println("444444444444444444=" + count);
        return null;
    }
}
