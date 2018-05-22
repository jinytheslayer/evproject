import com.aev.dao.DbUserDAO;
import com.aev.dao.UserDAO;
import com.aev.model.Gender;
import com.aev.model.User;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.cayenne.CayenneRuntimeException;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class DBTest {

    private final String url = "jdbc:h2:mem:test";
    private final String login = "sa";
    private final String password = "";
    private final String configurationCayenne = "cayenne-test.xml";
    private Logger logger;
    private final String getCountUsers = "getCountUsers";


    @Before
    public void createDB() throws ClassNotFoundException, SQLException, LiquibaseException {

        Connection connection = DriverManager.getConnection(url, login, password);

        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new liquibase.database.jvm.JdbcConnection(connection));

        Liquibase liquibase = new liquibase.Liquibase("db.changelog.xml", new ClassLoaderResourceAccessor(), database);

        liquibase.update(new Contexts());

//        logger = Logger.getLogger("DBtest");
//        if (logger.isDebugEnabled()) {
//            logger.log(Level.DEBUG, "Создание таблицы USER");
//        }
//        String createTable = "createTable";
//        String insertUsers = "insertUsers";
//        UserDAO dao = new DbUserDAO(configurationCayenne);
//        ServerRuntime cayenneRuntime = new ServerRuntime(configurationCayenne);
//        ObjectContext context = cayenneRuntime.getContext();
//        Query queryCreate = new NamedQuery(createTable);
//
//        List<Long> userID = new ArrayList<>();
//        for(long i = 1; i <= 10; i++) {
//            userID.add(i);
//        }
//
//        Map<String, List<Long>> map = new HashMap<>();
//        map.put("users_id", userID);
//        Query queryInsert = new NamedQuery(insertUsers, map);
//
//        context.performQuery(queryCreate);
//       // context.commitChanges();
//        context.performQuery(queryInsert);
//        //context.commitChanges();
//        }
//        if (logger.isDebugEnabled()) {
//            logger.debug("Создана таблица User c количеством записей: " +map.size());
    }

    @Test
    public void testAddUsers() {
        try {
            UserDAO dao = new DbUserDAO(configurationCayenne);
            long prevCountUsers = dao.getCountUsers();
            List<User> users = new ArrayList<>();
            users.add(new User(prevCountUsers + 19, "login", "password", "first_name", "last_name", Gender.FEMALE, "2000-11-11", "description"));
            users.add(new User(prevCountUsers + 16, "login", "password", "first_name", "last_name", Gender.FEMALE, "2000-11-11", "description"));
            dao.addUsers(users);
            long lastCountUsers = dao.getCountUsers();
            assertTrue(lastCountUsers - prevCountUsers == 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = CayenneRuntimeException.class)
    public void testAddUserWithExistingId() throws SQLException {
        UserDAO dao = new DbUserDAO(configurationCayenne);
        long prevCountUsers = dao.getCountUsers();
        List<User> users = new ArrayList<>();
        users.add(new User(1, "login", "password", "first_name", "last_name", Gender.FEMALE, "2000-11-11", "description"));
        dao.addUsers(users);
        assertTrue(dao.getCountUsers() - prevCountUsers == 2);
    }

    @Test
    public void testDeleteUsers() throws SQLException {
        DbUserDAO dao = new DbUserDAO(configurationCayenne);
        List<Long> users = new ArrayList<>();
        for (long i = 5; i < 10; i++)
            users.add(i);
        long prevCountUsers = dao.getCountUsers();
        dao.deleteUsers(users);
        long lastCountUsers = dao.getCountUsers();
        assertTrue((prevCountUsers - lastCountUsers) == 5);
    }

    @Test
    public void testSearchUsersByAllParameters() throws SQLException {
        UserDAO dao = new DbUserDAO(configurationCayenne);
        long prevCountUsers = dao.getCountUsers();
        List<User> users = dao.searchUsersByParameters(null, null, null);
        int a = users.size();
        assertTrue(a == prevCountUsers  );
    }
}