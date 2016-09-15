package ru.javawebinar.topjava.web.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Микитенко on 14.09.2016.
 */
public class CharsetEncodingFilter implements Filter {
    private String reqEncoding;
    private String respEncoding;
    private String contentType;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        reqEncoding = filterConfig.getInitParameter("requestEncoding");
        if(reqEncoding == null) reqEncoding = "UTF-8";
        if(respEncoding == null) respEncoding = "UTF-8";
        if(contentType == null) contentType = "text/html; charset=UTF-8";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (null == request.getCharacterEncoding()) {
            request.setCharacterEncoding(reqEncoding);
        }
        // Set the default response content type and encoding
        response.setContentType(contentType);
        response.setCharacterEncoding(respEncoding);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
