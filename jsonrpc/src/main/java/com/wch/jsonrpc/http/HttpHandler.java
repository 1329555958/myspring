package com.wch.jsonrpc.http;

import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author weichunhe
 *         Created on 2016/8/25.
 * @version 1.0
 */
public class HttpHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestURI());
        System.out.println("哈哈哈，我知道怎么编写spring的http映射了!");
    }
}
