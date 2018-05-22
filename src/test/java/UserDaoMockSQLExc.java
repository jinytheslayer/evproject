import com.aev.dao.UserDAO;
import com.aev.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDaoMockSQLExc implements UserDAO {
    @Override
    public void addUsers(List<User> users) throws SQLException {
        throw new SQLException("test");
    }

    @Override
    public void deleteUsers(List<Long> usersID) throws SQLException {
        throw new SQLException("test");
    }

    @Override
    public boolean isUserExists(Long userID) throws SQLException {
        throw new SQLException("test");
    }

    @Override
    public List<User> searchUsersByParameters(String login, String firstName, String lastName) throws SQLException {
        throw new SQLException("test");
    }

    @Override
    public long getCountUsers() throws SQLException  {
        throw new SQLException("test");
    }
}
