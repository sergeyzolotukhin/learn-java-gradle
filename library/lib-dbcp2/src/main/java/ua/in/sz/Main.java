package ua.in.sz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";

        setupDriver(url);

        Connection con = DriverManager.getConnection("jdbc:apache:commons:dbcp:HyPool");

        DatabaseMetaData meta = con.getMetaData();
        log.info("Database Connection Info: ");
        log.info("   Server name: "+meta.getDatabaseProductName());
        log.info("   Server version: "+meta.getDatabaseProductVersion());
        log.info("   Driver name: "+ meta.getDriverName());
        log.info("   Driver version: "+ meta.getDriverVersion());
        log.info("   JDBC major version: "+ meta.getJDBCMajorVersion());
        log.info("   JDBC minor version: "+ meta.getJDBCMinorVersion());

        con.close();

        destroyDriver();
    }

    public static void logStatistics() throws Exception {
        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
        ObjectPool<? extends Connection> op = driver.getConnectionPool("HyPool");
        log.info("DBCP PoolingDriver Info: ");
        log.info("   Active Connections: " + op.getNumActive());
        log.info("   Idle Connections: " + op.getNumIdle());
    }

    public static void setupDriver(String url) throws Exception {
        ConnectionFactory cf = new DriverManagerConnectionFactory(url);
        PoolableConnectionFactory pcf = new PoolableConnectionFactory(cf, null);

        ObjectPool<PoolableConnection> op = new GenericObjectPool<>(pcf);
        pcf.setPool(op);

        Class.forName("org.apache.commons.dbcp2.PoolingDriver");
        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
        driver.registerPool("HyPool", op);
    }

    public static void destroyDriver() throws Exception {
        PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
        driver.closePool("HyPool");
    }
}