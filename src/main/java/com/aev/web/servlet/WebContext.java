package com.aev.web.servlet;

import com.aev.app.UserBusinessObject;
import com.aev.dao.DbUserDAO;
import com.aev.dao.UserDAO;

public class WebContext {

    private static volatile WebContext instance;

    private static UserDAO dao;
    private static UserBusinessObject businessObject;

    private final static String config = "cayenne-conf.xml";

    private WebContext() {
        dao = new DbUserDAO(config);
        businessObject = new UserBusinessObject(dao);
    }

    public static WebContext getInstance() {
        WebContext localInstance = instance;
        if (localInstance == null) {
            synchronized (WebContext.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new WebContext();
                }
            }
        }
        return localInstance;
    }


    public UserDAO getUserDAO() {
        return dao;
    }

    public UserBusinessObject getUserBO() {
        return businessObject;
    }

}
