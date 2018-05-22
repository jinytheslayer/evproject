package com.aev.web.servlet;


import com.aev.app.BusinessException;
import com.aev.model.User;
import com.aev.util.XmlWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetUsersServlet extends HttpServlet {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("UserBusinessLogger");

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String login = (req.getParameter("login") != null) && !req.getParameter("login").equals("") ? req.getParameter("login") : null;
        String firstName = (req.getParameter("firstname") != null) && !req.getParameter("firstname").equals("") ? req.getParameter("firstname") : null;
        String lastName = (req.getParameter("lastname") != null) && !req.getParameter("lastname").equals("") ? req.getParameter("lastname") : null;

        List<User> users;
        try {
            users = WebContext.getInstance().getUserBO().dsUserFindListByParam(login, firstName, lastName);
        } catch (BusinessException e) {
            logger.error(e, e);
            res.sendError(500);
            return;
        }
        String s = XmlWriter.usersToXml(users);
        res.setContentType("text/xml;charset=UTF-8");
        res.setContentLength(s.getBytes("UTF-8").length);
        try (PrintWriter writer = res.getWriter()) {
            writer.append(s);
            writer.flush();
        }
    }
}
