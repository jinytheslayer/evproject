package DBUser;

import com.aev.util.TemplateVelocity;
import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBUnitTest extends DBTestCase {

    private IDatabaseTester tester;
    private Properties prop;
    private IDataSet beforeData;
    private ITable resultTable;
    private static final Logger logger = Logger.getLogger("DBUnitTest");

    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(prop.getProperty("db.driver"),
                prop.getProperty("db.url"),
                prop.getProperty("db.username"),
                prop.getProperty("db.password"));
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        beforeData = builder.build(new File("src/main/resources/DBUnit/dataset.xml"));
        resultTable = builder.build(new File("src/main/resources/DBUnit/resultset.xml")).getTable("USERTEST");
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    public DBUnitTest() {
        super();
        prop = new Properties();
        try {
            prop.load(Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream("DBUnit/db.config.properties"));
        } catch (IOException e) {
            logger.error(e, e);
            return;
        }
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, prop.getProperty("db.driver"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, prop.getProperty("db.url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, prop.getProperty("db.username"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, prop.getProperty("db.password"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA, "");
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }

  /*  public void testSearchUsersByAllParameters() throws Exception {
        VelocityContext context = new VelocityContext();
        Template t = TemplateVelocity.getTemplateVelocity("DBUnit/searchByParameters.vm");
        Map<String, String> map = new HashMap<>();
        StringWriter writer = new StringWriter();
        map.put("login", "2");
        map.put("first_name", "a");
        map.put("last_name", null);
        context.put("paramMap", map);
        t.merge(context, writer);
        String sql = writer.toString();

        ITable actualTable = tester.getConnection().createQueryTable("USER", sql);
        Assertion.assertEquals(resultTable, actualTable);
    }*/

}
