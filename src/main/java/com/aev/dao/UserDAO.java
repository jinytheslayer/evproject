package com.aev.dao;

import com.aev.model.User;
import org.apache.cayenne.configuration.server.ServerRuntime;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUsers(List<User> users) throws SQLException;
    void deleteUsers(List<Long> usersID) throws SQLException;
    boolean isUserExists(Long userID) throws SQLException;
    List<User> searchUsersByParameters(String login, String firstName, String lastName) throws SQLException;
    long getCountUsers() throws SQLException;
}
