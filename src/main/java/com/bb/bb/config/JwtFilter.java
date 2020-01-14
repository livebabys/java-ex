package com.bb.bb.config;


import com.bb.bb.common.JwtHelper;
import com.bb.bb.common.Result;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class JwtFilter implements Filter {

    private JwtHelper jwtHelper;

    private List<String> urls = null;

    private static final org.springframework.util.PathMatcher pathMatcher = new AntPathMatcher();

    public JwtFilter(JwtHelper jwtHelper,String[] authorisedUrls ){
        this.jwtHelper = jwtHelper;
        urls = Arrays.asList(authorisedUrls);
        System.out.println("urls:  "+urls);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException,ServletException{
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        if("OPTIONS".equals(httpRequest.getMethod())){
            httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, Token");
            httpResponse.setHeader("Access-Control-Allow-Methods", "OPTIONS,GET,POST,DELETE,PUT");
        }

        String spath = httpRequest.getServletPath();
        try{
            for(String url:urls){
                System.out.println("url:"+url);
                System.out.println("path:"+spath);
                if(pathMatcher.match(url,spath)){
                    Object token = jwtHelper.validateTokenAndGetClaims(httpRequest);
                    HttpSession session = httpRequest.getSession();

                    session.setAttribute("username",((Map) token).get("username"));
                    session.setAttribute("uid",((Map) token).get("uid"));
                    if(token != null){
                        chain.doFilter(request,response);
                        return;
                    }else{
                        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"未授权或者授权已经过期");
                        return;
                    }
                }else{
                    chain.doFilter(request,response);
                    return;
                }
            }
            chain.doFilter(request,response);
            return;
        }catch(NullPointerException e){
            System.out.println(e.getMessage());
            httpResponse.getWriter().println(new Result<>(101, "token不能为空"));
        }catch (MalformedJwtException e){
            System.out.println(e.getMessage());
            httpResponse.getWriter().println(new Result<>(101, "token不正确"));
        }


    }

    @Override
    public void destroy(){

    }
}
