package com.aev.util;

import com.aev.model.Notification;
import com.aev.model.User;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.StringWriter;
import java.util.List;

public class XmlWriter {
    public  static String usersToXml(List<User> users) {
        VelocityContext context = new VelocityContext();
        Template t = TemplateVelocity.getTemplateVelocity("xmlUsers.vm");
        StringWriter writer = new StringWriter();
        context.put("listUsers", users);
        t.merge(context, writer);
        return writer.toString();
    }

    public static String notificationsToXML(List<Notification> notifications) {
        VelocityContext context = new VelocityContext();
        Template t = TemplateVelocity.getTemplateVelocity("xmlNotification.vm");
        StringWriter writer = new StringWriter();
        context.put("notificationList", notifications);
        t.merge(context, writer);
        return writer.toString();
    }
}
