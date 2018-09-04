import command.Command;
import command.impl.ItemsCommand;
import command.impl.LoginCommand;
import command.impl.UsersCommand;
import model.CommandEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DispatcherServlet.class);

    private static final Map<CommandEnum, Command> commands = new HashMap<>();

    @Override
    public void init() {
        logger.debug("DispatcherServlet init!");
        commands.put(CommandEnum.LOGIN, new LoginCommand());
        commands.put(CommandEnum.USERS, new UsersCommand());
        commands.put(CommandEnum.ITEMS, new ItemsCommand());
    }

    @Override
    public void destroy() {
        logger.debug("DispatcherServlet destroy!");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String command = request.getParameter("command");
        Command commandAction = null;
        try {
            commandAction = commands.get(CommandEnum.getCommand(command));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }
        if (commandAction != null) {
            try {
                String page = commandAction.execute(request, response);
                if (page != null) {
                    getServletContext().getRequestDispatcher(page).forward(request, response);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } else {
            logger.error("Command " + command + " does not exists!");
        }
    }
}
