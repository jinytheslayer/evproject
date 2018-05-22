package com.aev.web.services;

import com.aev.app.BusinessException;
import com.aev.model.*;
import com.aev.web.services.soap.Notifications;
import com.aev.web.services.soap.Parameters;
import com.aev.web.services.soap.UserIds;
import com.aev.web.services.soap.Users;
import com.aev.web.servlet.WebContext;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;


import java.io.IOException;
import java.util.List;

@WebService(endpointInterface = "com.aev.web.services.UserService")
public class UserServiceImpl implements UserService {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("UserBusinessLogger");

    @Resource
    private WebServiceContext context;

    @Override
    public Notifications dsUserMassCreate(Users users) {
        List<Notification> notifications = WebContext.getInstance().getUserBO().dsUserMassCreate(users.getUsers());
        return new Notifications(notifications);
    }

    @Override
    public Notifications dsUserMassDelete(UserIds userId) {
        List<Notification> notifications = WebContext.getInstance().getUserBO().dsUserMassDelete(userId.getUsers());
        return new Notifications(notifications);
    }

    @Override
    public Users dsUserFindListByParam(Parameters parameters) {
        try {
            return new Users(WebContext.getInstance().getUserBO().dsUserFindListByParam(parameters.getLogin(), parameters.getFirstName(), parameters.getLastName()));
        } catch (BusinessException e) {
            logger.error(e, e);
            MessageContext ctx = context.getMessageContext();
            HttpServletResponse response = (HttpServletResponse)
                    ctx.get(MessageContext.SERVLET_RESPONSE);
            try {
                response.sendError(500);
            } catch (IOException e1) {
                logger.error(e1, e1);
            }
            return null;
        }
    }
}
