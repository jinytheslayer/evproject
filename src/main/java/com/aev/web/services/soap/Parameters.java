package com.aev.web.services.soap;

import javax.xml.bind.annotation.XmlElement;

public class Parameters {
    @XmlElement(name = "login")
    private String login;
    @XmlElement(name = "firstName")
    private String firstName;
    @XmlElement(name = "lastName")
    private String lastName;

    public Parameters() {
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Parameters(String login, String firstName, String lastName) {
        this.lastName = lastName;
        this.login = login;
        this.firstName = firstName;

    }
}
