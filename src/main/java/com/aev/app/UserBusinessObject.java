package com.aev.app;

import com.aev.dao.UserDAO;
import com.aev.model.Notification;
import com.aev.model.User;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBusinessObject {

    private static final Logger logger = Logger.getLogger("UserBusinessLogger");

    private UserDAO dao;

    public UserBusinessObject(UserDAO dao) {
        this.dao = dao;
    }


    public List<Notification> dsUserMassCreate(List<User> users) {
        if (logger.isDebugEnabled()) {
            logger.debug("dsUserMassCreate: input: " + users);
        }

        List<Notification> notificationList = new ArrayList<>();
        List<User> legalUser = new ArrayList<>();
        try {
            for (User user : users) {
                if (user.getUserId() <= 0) {
                    notificationList.add(new Notification(user.getUserId(), 1, "Отсутствует обязательное поле USER_ID"));
                    continue;
                }
                if (user.getLogin() == null || user.getLogin().trim().isEmpty()) {
                    notificationList.add(new Notification(user.getUserId(), 1, "Отсутствует обязательное поле LOGIN"));
                    continue;
                }
                if (user.getDate() == null || user.getDate().trim().isEmpty()) {
                    notificationList.add(new Notification(user.getUserId(), 1, "Отсутствует обязательное поле DATE"));
                    continue;
                }
                if (user.getGender() == null) {
                    notificationList.add(new Notification(user.getUserId(), 1, "Отсутствует обязательное поле GENDER"));
                    continue;
                }
                if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
                    notificationList.add(new Notification(user.getUserId(), 1, "Отсутствует обязательное поле LAST_NAME"));
                    continue;
                }
                if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
                    notificationList.add(new Notification(user.getUserId(), 1, "Отсутствует обязательное поле PASSWORD"));
                    continue;
                }
                if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
                    notificationList.add(new Notification(user.getUserId(), 1, "Отсутствует обязательное поле FIRST_NAME"));
                    continue;
                }
                if (dao.isUserExists(user.getUserId())) {
                    notificationList.add(new Notification(user.getUserId(), 2, "Пользователь с таким идентификатором уже существует"));
                    continue;
                }
                legalUser.add(user);
            }

            dao.addUsers(legalUser);
            for (User us : legalUser) {
                notificationList.add(new Notification(us.getUserId(), 0));
            }
        } catch (SQLException e) {
            logger.error(e.toString());
            for (User us : users) {
                notificationList.add(new Notification(us.getUserId(), 3, "Системная ошибка: " + e.getMessage()));
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("dsUserMassCreate: output: " + notificationList);
        }
        return notificationList;
    }

    public List<Notification> dsUserMassDelete(List<Long> users) {
        if (logger.isDebugEnabled()) {
            logger.debug("dsUserMassDelete: input: " + users);
        }
        List<Notification> notificationList = new ArrayList<>();
        List<Long> usersToDelete = new ArrayList<>();
        try {
            for (Long userID : users) {
                if (!dao.isUserExists(userID)) {
                    notificationList.add(new Notification(userID, 4, "Пользователя с таким идентификатором не существует"));
                    continue;
                }
                usersToDelete.add(userID);
            }
            if (!usersToDelete.isEmpty())
                dao.deleteUsers(usersToDelete);

            for (Long userID : usersToDelete)
                notificationList.add(new Notification(userID, 0));

        } catch (SQLException e) {
            for (Long userID : users)
                notificationList.add(new Notification(userID, 3, "Системная ошибка: " + e.getMessage()));
            logger.error(e, e);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("dsUserMassCreate: output: " + notificationList);
        }
        return notificationList;
    }

    public List<User> dsUserFindListByParam(String login, String firstName, String lastName) throws BusinessException {
        if (logger.isDebugEnabled()) {
            logger.debug("dsUserFindListByParam: input: login = " + login + ", firstName = " + firstName + ", lastName = " + lastName);
        }
        try {
            List<User> users = dao.searchUsersByParameters(login, firstName, lastName);
            if (logger.isDebugEnabled()) {
                logger.debug("dsUserFindListByParam: output: " + users);
            }
            return users;
        } catch (SQLException e) {
            logger.error(e.toString());
            throw new BusinessException(e);
        }
    }

}
