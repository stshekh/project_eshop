package command.impl;

import com.gmail.sshekh.config.ConfigurationManager;
import command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ITEMS_PAGE_PATH);
    }
}
