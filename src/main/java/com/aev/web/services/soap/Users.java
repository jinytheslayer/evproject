package com.aev.web.services.soap;

import com.aev.model.User;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Users{

    @XmlElement(name="user")
    private List<User> users;

    public Users(List<User> users) {
        this.users = users;
    }

    public Users() {
    }

    public List<User> getUsers() {
        return users;
    }
}