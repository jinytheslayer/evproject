import com.aev.dao.UserDAO;
import com.aev.model.Gender;
import com.aev.model.User;

import java.sql.SQLException;
import java.util.*;

public class UserDAOMock implements UserDAO {

    private List<User> userList;


    public UserDAOMock() {
        userList = new ArrayList<>();
        userList.add(new User(1, "aaa", "aaa", "aaa", "aaa", Gender.MALE, "2009-12-12", "abc"));
        userList.add(new User(2, "bbb", "bbb", "bbb", "bbb", Gender.MALE, "2009-11-11", "abc"));
        userList.add(new User(3, "ccc", "ccc", "ccc", "ccc", Gender.FEMALE, "2009-10-10", "abc"));
        userList.add(new User(4, "ddd", "ddd", "ddd", "ddd", Gender.MALE, "2009-09-09", "abc"));
    }

    @Override
    public void addUsers(List<User> users) throws SQLException {
        userList.addAll(users);
    }

    @Override
    public void deleteUsers(List<Long> usersID) throws SQLException {
        for (int i = 0; i < usersID.size(); i++) {
            userList.remove(usersID.size() - 1 - i);
        }
    }

    @Override
    public boolean isUserExists(Long userID) throws SQLException {
        for (User u : userList) {
            if (u.getUserId().equals(userID)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public List<User> searchUsersByParameters(String login, String firstName, String lastName) throws SQLException {
        return null;
    }

    @Override
    public long getCountUsers() throws SQLException {
        return userList.size();
    }


}
