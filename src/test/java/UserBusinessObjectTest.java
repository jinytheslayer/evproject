import com.aev.app.UserBusinessObject;
import com.aev.dao.UserDAO;
import com.aev.model.Gender;
import com.aev.model.Notification;
import com.aev.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBusinessObjectTest {

    @Test
    public void dsUserMassCreateTest_OK() throws SQLException {
        UserDAO userDAO = new UserDAOMock();
        UserBusinessObject businessObject = new UserBusinessObject(userDAO);

        List<User> users = new ArrayList<>();

        users.add(new User(5, "aaa", "aaa", "aaa", "aaa", Gender.MALE, "2009-12-12", "abc"));

        List<Notification> notificationList = businessObject.dsUserMassCreate(users);

        Assert.assertTrue(notificationList.get(0).getNTFID() == 0);
    }

    @Test
    public void dsUserMassCreateTest_UserExists() throws SQLException {
        UserDAO userDAO = new UserDAOMock();
        UserBusinessObject businessObject = new UserBusinessObject(userDAO);

        List<User> userList = new ArrayList<>();

        userList.add(new User(1, "aaa", "aaa", "aaa", "aaa", Gender.MALE, "2009-12-12", "abc"));

        List<Notification> notificationList = businessObject.dsUserMassCreate(userList);

        Assert.assertTrue(notificationList.get(0).getNTFID() == 2);
    }

    @Test
    public void dsUserMassCreateTest_BadField_lastName() throws SQLException {
        UserDAO userDAO = new UserDAOMock();
        UserBusinessObject businessObject = new UserBusinessObject(userDAO);

        List<User> userList = new ArrayList<>();

        userList.add(new User(1, "aaa", "aaa", "aaa", null, Gender.MALE, "2009-12-12", "abc"));

        List<Notification> notificationList = businessObject.dsUserMassCreate(userList);

        Assert.assertTrue((notificationList.get(0).getNTFID() == 1) && (notificationList.get(0).getNTFMessage().equals("Отсутствует обязательное поле LAST_NAME")));

    }

    @Test
    public void dsUserMassCreateTest_SQLExc() throws SQLException{
        UserDAO userDAO = new UserDaoMockSQLExc();
        UserBusinessObject businessObject = new UserBusinessObject(userDAO);

        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "aaa", "aaa", "aaa", "aaa", Gender.MALE, "2009-12-12", "abc"));
        List<Notification> notificationList;

        notificationList = businessObject.dsUserMassCreate(userList);
        Assert.assertTrue(notificationList.get(0).getNTFID() == 3);
    }

    @Test
    public void dsUserMassDeleteTest_OK() throws SQLException {
        UserDAO userDAOMock = new UserDAOMock();
        UserBusinessObject userBusinessObject = new UserBusinessObject(userDAOMock);
        List<Long> users = new ArrayList<>();
        users.add(1L);
        users.add(2L);
        users.add(3L);
        users.add(4L);
        List<Notification> notificationList = userBusinessObject.dsUserMassDelete(users);
        boolean flag = true;
        for (Notification n : notificationList) {
            System.out.println(n);
            if (n.getNTFID() != 0)
                flag = false;
        }

        Assert.assertTrue(flag);
    }

    @Test
    public void dsUserMassDeleteTest_UserUnknown() throws SQLException {
        UserDAO userDAOMock = new UserDAOMock();
        UserBusinessObject userBusinessObject = new UserBusinessObject(userDAOMock);
        List<Long> users = new ArrayList<>();
        users.add(5L);
        List<Notification> notificationList = userBusinessObject.dsUserMassDelete(users);
        Assert.assertTrue((notificationList.get(0).getUserId() == 5L) && (notificationList.get(0).getNTFID() == 4) && (notificationList.get(0).getNTFMessage().equals("Пользователя с таким идентификатором не существует")));
        System.out.println("User_ID " + notificationList.get(0).getUserId() + " NTFID" + notificationList.get(0).getNTFID() + " " + notificationList.get(0).getNTFMessage());
    }

    @Test
    public void dsUserMassDeleteTest_SQLExc() throws SQLException {
        UserDAO userDAO = new UserDaoMockSQLExc();
        UserBusinessObject userBusinessObject = new UserBusinessObject(userDAO);
        List<Long> users = new ArrayList<>();
        users.add(5L);
        List<Notification> notificationList = userBusinessObject.dsUserMassDelete(users);
        Assert.assertTrue((notificationList.get(0).getNTFID() == 3));
    }
}
