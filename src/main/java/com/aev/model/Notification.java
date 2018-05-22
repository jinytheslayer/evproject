package com.aev.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notification")
public class Notification {

    @XmlElement(required = true, name = "userID")
    private long userId;
    @XmlElement(required = true, name = "ntfID")
    private int ntfID;
    @XmlElement(name = "ntfMessage")
    private String nTFMessage;

    private Notification(){}

    public Notification(long userId, int ntfID, String nTFMessage) {
        this.userId = userId;
        this.ntfID = ntfID;
        this.nTFMessage = nTFMessage;
    }

    public Notification(long userId, int ntfID) {
        this.userId = userId;
        this.ntfID = ntfID;
    }

    @Override
    public String toString() {
        return "UserID: " + userId + " NtfID: " + ntfID + ((nTFMessage != null) ? (" Message: " + nTFMessage) : "");
    }

    public long getUserId() {
        return userId;
    }

    public int getNTFID() {
        return ntfID;
    }

    public String getNTFMessage() {
        return nTFMessage;
    }
}
