package ua.in.sz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("start");
        String url = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres";
        setupDriver(url);

        ExecutorService executorService = Executors.newFixedThreadPool(50);
        List<Callable<String>> callables = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            callables.add(Main::queryConnection);
        }

        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            for (Future<String> future : futures) {
                log.info("future.get = " + getCount(future));
            }
        } finally {
            executorService.shutdown();
        }

        destroyDriver();
        log.info("end");
    }

    private static String getCount(Future<String> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return e.getMessage();
        }
    }

    private static String queryConnection() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:apache:commons:dbcp:HyPool");

        StringBuilder sb = new StringBuilder();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT count(*) as connection_count FROM pg_stat_activity where backend_type in ('client backend')");
        while (rs.next()) {
            sb.append(rs.getLong("connection_count"));
        }
        rs.close();
        st.close();

        con.close();

        sb.append(" : [").append(Thread.currentThread().getName()).append("]");

        return sb.toString();
    }

    private static String queryMetadata() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:apache:commons:dbcp:HyPool");

        DatabaseMetaData meta = con.getMetaData();
        String name = meta.getDatabaseProductName();

        con.close();

        return "Server name: " + name;
    }

    private static void logMetadata() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:apache:commons:dbcp:HyPool");

        DatabaseMetaData meta = con.getMetaData();
        log.info("Database Connection Info: ");
        log.info("   Server name: " + meta.getDatabaseProductName());
        log.info("   Server version: " + meta.getDatabaseProductVersion());
        log.info("   Driver name: " + meta.getDriverName());
        log.info("   Driver version: " + meta.getDriverVersion());
        log.info("   JDBC major version: " + meta.getJDBCMajorVersion());
        log.info("   JDBC minor version: " + meta.getJDBCMinorVersion());

        con.close();
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

        GenericObjectPoolConfig<PoolableConnection> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(50);
        ObjectPool<PoolableConnection> op = new GenericObjectPool<>(pcf, config);
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