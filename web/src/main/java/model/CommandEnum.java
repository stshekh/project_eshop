package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandEnum {
    LOGIN,
    USERS,
    ADDUSER,
    UPDATEUSER,
    DELETEUSER, ITEMS;

    private static final Logger logger = LogManager.getLogger(CommandEnum.class);

    public static CommandEnum getCommand(String command) {
        try {
            return CommandEnum.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Command does not found", e);
        }
        return null;
    }
}
