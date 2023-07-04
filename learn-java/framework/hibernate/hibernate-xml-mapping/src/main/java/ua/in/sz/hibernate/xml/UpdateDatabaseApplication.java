package ua.in.sz.hibernate.xml;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;

@Slf4j
public class UpdateDatabaseApplication {
    public static final String CLUSTER_VALUE_CHANGE_LOG_FILE = "db-changelog-value-cluster.xml";
    public static final String NON_CLUSTER_CHANGE_LOG_FILE = "db-changelog-non-cluster.xml";

    public static void main(String[] args) {
        Sessions.doInConnection(con -> updateDatabase(con, NON_CLUSTER_CHANGE_LOG_FILE));
    }

    private static void updateDatabase(Connection con, String changeLogFile) throws LiquibaseException {
        JdbcConnection jdbcCon = new JdbcConnection(con);
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcCon);
        Liquibase liquibase = new Liquibase(changeLogFile, new ClassLoaderResourceAccessor(), database);
        liquibase.update("test");
    }
}
