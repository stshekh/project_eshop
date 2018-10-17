package com.gmail.sshekh.service.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaginationUtil {

    private static final Logger logger = LogManager.getLogger(PaginationUtil.class);

    public static final int NEWS_PER_PAGE = 5;
    public static final int COMMENTS_PER_PAGE = 5;
    public static final int USERS_PER_PAGE = 2;
    public static final int ITEMS_PER_PAGE = 5;
    public static final int ORDERS_PER_PAGE = 5;

    public static Integer getNumberOfPages(Integer totalEntities, int maxEntitiesPerPage) {
        int totalPages = 0;
        try {
            if (totalEntities > 0 && totalEntities <= maxEntitiesPerPage) {
                totalPages = 1;
            } else if (totalEntities % maxEntitiesPerPage == 0) {
                totalPages = totalEntities / maxEntitiesPerPage;
            } else {
                totalPages = (totalEntities / maxEntitiesPerPage) + 1;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return totalPages;
    }
}
