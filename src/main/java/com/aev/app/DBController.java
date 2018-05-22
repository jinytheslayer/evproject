package com.aev.app;

import com.aev.model.Gender;
import com.aev.model.User;
import com.aev.util.TemplateVelocity;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.CayenneRuntime;
import org.apache.cayenne.configuration.server.ServerRuntime;
import org.apache.cayenne.query.NamedQuery;
import org.apache.cayenne.query.Query;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.dbunit.DatabaseUnitException;
import org.h2.jdbcx.JdbcConnectionPool;
import sun.rmi.log.LogInputStream;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBController {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {


//cayenne example
//        CayenneRuntime cayenneRuntime = new ServerRuntime("cayenne-conf.xml");
//        ObjectContext context = cayenneRuntime.getContext();
//        List<User> users = new ArrayList<>();
//        users.add(new User(100L, "g","g","g","g", Gender.MALE, "2006-09-09","abc"));
//        users.add(new User(200L, "g","g","g","g", Gender.MALE, "2006-09-09","abc"));
//        users.add(new User(392371L, "g","g","g","g", Gender.MALE, "2006-09-09","abc"));
//        users.add(new User(412312L, "g","g","g","g", Gender.MALE, "2006-09-09","abc"));
//        Map<String,Object> map = new HashMap<>();
//        map.put("listUsers",users);
//        Query query = new NamedQuery("addUsers", map);
//
//        context.performQuery(query);

//jdbc connection example
//        Class.forName("org.h2.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery("SELECT * FROM USER");
//        while (rs.next()) {
//            System.out.println(rs.getLong("USER_ID") + " " + rs.getString("FIRST_NAME") + " " + rs.getString("LAST_NAME"));
//        }

//JDBC connection pool example
//        JdbcConnectionPool connectionPool = JdbcConnectionPool.create("jdbc:h2:tcp://localhost/~/test", "sa", "");
//        List<String> sqlList = new ArrayList<>();
//        String a = "insert into user values (30, 'a', 'a','a','a','male','2009-09-09', 'abc')";
//        String b = "insert into user values (31, 'a', 'a','a','a','male','2009-09-09', 'abc')";
//        String c = "insert into user values (34, 'a', 'a','a','a','male','2009-09-09', 'abc')";
//        sqlList.add(a);
//        sqlList.add(b);
//        sqlList.add(c);
//        for (String sql : sqlList) {
//            Connection connection = connectionPool.getConnection();
//            Statement st = connection.createStatement();
//            st.execute(sql);
//        }

        //liquibase example
//        Connection connection = DriverManager.getConnection(url, login, password);
//        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new liquibase.database.jvm.JdbcConnection(connection));
//        Liquibase liquibase = new liquibase.Liquibase("db.changelog.xml", new ClassLoaderResourceAccessor(), database);
//        liquibase.update(new Contexts());

//velocity example
//        List<User> users = new ArrayList<>();
//        users.add(new User(100L, "g","g","g","g", Gender.MALE, "2006-09-09","abc"));
//        users.add(new User(200L, "g","g","g","g", Gender.MALE, "2006-09-09","abc"));
//        users.add(new User(392371L, "g","g","g","g", Gender.MALE, "2006-09-09","abc"));
//        users.add(new User(412312L, "g","g","g","g", Gender.MALE, "2006-09-09","abc"));
//
//        StringWriter writer = new StringWriter();
//
//        VelocityEngine engine = new VelocityEngine();
//        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
//        engine.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
//        engine.init();
//
//        VelocityContext context = new VelocityContext();
//        Template t = engine.getTemplate("xmlUsers.vm");
//        context.put("listUsers", users);
//        t.merge(context, writer);
//
//        System.out.println(writer.toString());



    }
}
