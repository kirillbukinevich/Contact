package com.itechart.bukinevi.web.filters;

/**
 * Created by aefrd on 28.09.2016.
 */
import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}