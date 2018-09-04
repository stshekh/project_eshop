import com.gmail.sshekh.config.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(ExceptionServlet.class);

    @Override
    public void init() {
        logger.debug("ExceptionServlet init!");
    }

    @Override
    public void destroy() {
        logger.debug("ExceptionServlet destroy!");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Throwable throwable = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String servletName = (String) request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        logger.debug("Error information");
        logger.info("The status code : " + statusCode);
        logger.info("Servlet Name : " + servletName);
        logger.info("Exception Type : " + throwable.getClass().getName());
        logger.info("The request URI: " + requestUri);
        throwable.printStackTrace();

        String page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERRORS_PAGE_PATH);
        getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
