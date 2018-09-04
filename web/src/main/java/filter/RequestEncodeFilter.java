package filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class RequestEncodeFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(RequestEncodeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("Request response encoder Filter object has been created");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
        response.setContentType("text/html; charset=UTF-8");
    }

    @Override
    public void destroy() {
    }
}