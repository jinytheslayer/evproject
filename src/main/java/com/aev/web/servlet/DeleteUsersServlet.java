package com.aev.web.servlet;

import com.aev.model.Notification;
import com.aev.util.XmlWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteUsersServlet extends HttpServlet {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("UserBusinessLogger");

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        List<Long> listUsersID = new ArrayList<>();
        Map allMap = req.getParameterMap();
        int size = allMap.size();
        for (int i = 1; i <= size; i++) {
            try {
                listUsersID.add(Long.parseLong(req.getParameter("id_" + i)));
            } catch (NumberFormatException e) {
                logger.error(e, e);
                res.sendError(400);
                return;
            }
        }

        List<Notification> notificationList = WebContext.getInstance().getUserBO().dsUserMassDelete(listUsersID);
        res.setContentType("text/xml;charset=UTF-8");
        String s = XmlWriter.notificationsToXML(notificationList);
        res.setContentLength(s.getBytes("UTF-8").length);
        try (PrintWriter writer = res.getWriter()) {
            writer.append(s);
            writer.flush();
        }
    }
}
