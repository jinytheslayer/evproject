package com.aev.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;
@XmlRootElement(name="user")
public class User {

    @XmlElement(required=true, name="id")
    private Long userId;
    @XmlElement(required=true, name="login")
    private String login;
    @XmlElement(required=true, name="password")
    private String password;
    @XmlElement(required=true, name="firstName")
    private String firstName;
    @XmlElement(required=true, name="lastName")
    private String lastName;
    @XmlElement(required=true, name="gender")
    private Gender gender;
    @XmlElement(required=true, name="date")
    private String date;
    @XmlElement(name="description")
    private String description;

    private User() {}


    public User(long userId, String login, String password, String firstName, String lastName, Gender gender, String date, String description) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.date = date;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add(userId.toString()).add(login).add(password).add(firstName).add(lastName)
                .add((gender != null) ? gender.toString() : "no gender").add(date).add(description);
        return stringJoiner.toString();
    }
}
