package com.aev.web.servlet;

import com.aev.model.Gender;
import com.aev.model.Notification;
import com.aev.model.User;
import com.aev.util.XmlWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddUsersServlet extends HttpServlet {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("UserBusinessLogger");

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<User> listUsers = new ArrayList<>();
        int size = 0;
        Map allMap = req.getParameterMap();
        Set<String> set = allMap.keySet();
        for (String s : set) {
            if (s.startsWith("id_"))
                size++;
        }

        Long id;
        for (int i = 1; i <= size; i++) {
            try {
                id = Long.parseLong(req.getParameter("id_" + i));
            } catch (NumberFormatException e) {
                logger.error(e, e);
                res.sendError(400);
                return;
            }
            String login = req.getParameter("login_" + i);
            String password = req.getParameter("password_" + i);
            String firstName = req.getParameter("firstName_" + i);
            String lastName = req.getParameter("lastName_" + i);
            String gender = req.getParameter("gender_" + i);
            String birthDate = req.getParameter("birthDate_" + i);
            String description = req.getParameter("description_" + i);
            listUsers.add(new User(id, login, password, firstName, lastName, Gender.valueOf(gender.toUpperCase()), birthDate, description));
        }

        List<Notification> notificationList = WebContext.getInstance().getUserBO().dsUserMassCreate(listUsers);
        res.setContentType("text/xml;charset=UTF-8");

        String s = XmlWriter.notificationsToXML(notificationList);
        res.setContentLength(s.getBytes("UTF-8").length);

        try (PrintWriter writer = res.getWriter()) {
            writer.append(s);
            writer.flush();
        }

    }
}
