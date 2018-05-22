package com.aev.listener;


import com.aev.web.servlet.WebContext;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger("UserBusinessLogger");

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            WebContext.getInstance().getUserDAO().getCountUsers();
        } catch (SQLException e) {
            logger.error(e, e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}