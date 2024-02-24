package ua.in.sz;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("start");
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=postgres";

        HikariDataSource dateSource = createDateSource(url);

        ExecutorService executorService = Executors.newFixedThreadPool(50);
        List<Callable<String>> callables = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            callables.add(() -> queryConnection(dateSource));
        }

        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            for (Future<String> future : futures) {
                try {
                    String result = future.get();
                    log.info("Get result: {}", result);
                } catch (InterruptedException | ExecutionException e) {
                    log.info("exception : " + e.getMessage());
                }

            }
        } finally {
            executorService.shutdown();
        }

        dateSource.close();

        log.info("end");
    }

    private static String queryConnection(DataSource dateSource) throws SQLException {
        Connection con = dateSource.getConnection();

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

    private static HikariDataSource createDateSource(String url) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
//        config.setUsername( "database_username" );
//        config.setPassword( "database_password" );
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return new HikariDataSource(config);
    }
}