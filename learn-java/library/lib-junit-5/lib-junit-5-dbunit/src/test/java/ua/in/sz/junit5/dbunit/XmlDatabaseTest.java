package ua.in.sz.junit5.dbunit;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import javax.sql.DataSource;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class XmlDatabaseTest {
    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    @BeforeClass
    public static void createSchema() throws Exception {
        RunScript.execute(JDBC_URL, USER, PASSWORD,
                "src/test/resources/dbunit/schema.sql", StandardCharsets.UTF_8, false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File("src/test/resources/dbunit/data.xml"));

        IDatabaseTester tester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        tester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        tester.setDataSet(dataSet);
        tester.onSetup();
    }

    @Test
    public void givenDataSet_whenSelect_thenFirstTitleIsGreyTShirt() throws SQLException {
        Connection connection = dataSource().getConnection();
        ResultSet rs = connection.createStatement().executeQuery("select * from ITEMS where id = 1");

        Assertions.assertTrue(rs.next());
        Assertions.assertEquals("Grey T-Shirt", rs.getString("title"));
    }

    private DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}
