package com.aev.web.services.soap;

import com.aev.model.Notification;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Notifications {

    @XmlElement(name = "notification")
    private List<Notification> notificationList;

    public Notifications(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public Notifications(){}


    public List<Notification> getNotificationList() {
        return notificationList;
    }
}
