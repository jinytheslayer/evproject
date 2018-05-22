package com.aev.web.services.soap;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class UserIds {
    @XmlElement(name="userId")
    private List<Long> userIds;

    public UserIds(List<Long> usersId) {
        this.userIds = usersId;
    }

    public UserIds() {
    }

    public List<Long> getUsers() {
        return userIds;
    }
}
