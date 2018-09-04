package filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        logger.debug("LogFilter init!");
    }

    @Override
    public void destroy() {
        logger.debug("LogFilter destroy!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.info(
                "#INFO " + new Date() + " URL =" + req.getRequestURL() + (req.getQueryString() == null ? "" : "?" + req.getQueryString())
        );
        chain.doFilter(request, response);
    }

}
