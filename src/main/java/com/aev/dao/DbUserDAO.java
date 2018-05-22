package com.aev.dao;

import com.aev.model.Gender;
import com.aev.model.User;
import org.apache.cayenne.DataRow;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.NamedQuery;
import org.apache.cayenne.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUserDAO implements UserDAO {

    private final String addUsersQueryNameInUserMap = "addUsers";
    private final String deleteUsersQueryNameInUserMap = "deleteUsers";
    private final String searchByParametersUsersQueryNameInUserMap = "searchByParameters";
    private final ServerRuntime cayenneRuntime;
    private final String isUser = "isUser";
    private final String getCountUsers = "getCountUsers";


    public DbUserDAO(String configurationFileName) {
        cayenneRuntime = new ServerRuntime(configurationFileName);
    }

    @Override
    public void addUsers(List<User> users) throws SQLException {
        ObjectContext context = cayenneRuntime.getContext();
        Map<String, Object> usersMap = new HashMap<>();
        usersMap.put("listUsers", users);
        Query query = new NamedQuery(addUsersQueryNameInUserMap, usersMap);
        context.performQuery(query);
    }

    @Override
    public void deleteUsers(List<Long> users) throws SQLException {
        ObjectContext context = cayenneRuntime.getContext();
        Map<String, Object> ids = new HashMap<>();
        ids.put("paramList", users);

        Query query = new NamedQuery(deleteUsersQueryNameInUserMap, ids);
        context.performQuery(query);
    }

    @Override
    public List<User> searchUsersByParameters(String login, String firstName, String lastName) throws SQLException {
        List<DataRow> usersObject;
        List<User> users = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("first_name", firstName);
        map.put("last_name", lastName);
        ObjectContext context = cayenneRuntime.getContext();
        Query query = new NamedQuery(searchByParametersUsersQueryNameInUserMap, map);
        usersObject = context.performQuery(query);
        for (DataRow user : usersObject) {
            users.add(new User((long) user.get("USER_ID"), (String) user.get("LOGIN"), (String) user.get("PASSWORD"), (String) user.get("FIRST_NAME"), (String) user.get("LAST_NAME"), user.get("GENDER") == Gender.MALE ? Gender.MALE : Gender.FEMALE, user.get("BIRTH_DATE").toString(), (String) user.get("DESCRIPTION")));
        }
        return users;

    }

    @Override
    public boolean isUserExists(Long userID) throws SQLException {
        ObjectContext context = cayenneRuntime.getContext();
        Map<String, Long> map = new HashMap<>();
        map.put("user_id", userID);
        Query query = new NamedQuery(isUser, map);
        List<DataRow> list;
        list = context.performQuery(query);
        return (Long) list.get(0).get("COUNT(*)") != 0; //values={COUNT(*)=0}, version=-9223372036854775806, replaces=-9223372036854775808
    }

    @Override
    public long getCountUsers() throws SQLException {
        ObjectContext context = cayenneRuntime.getContext();
        Query query = new NamedQuery(getCountUsers);
        List<DataRow> list;
        list = context.performQuery(query);
        return (Long) list.get(0).get("COUNT(*)"); //values={COUNT(*)=0}, version=-9223372036854775806, replaces=-9223372036854775808
    }
}

