package ua.in.sz;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
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
import java.util.concurrent.TimeUnit;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("start");
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=postgres";

        HikariDataSource dateSource = createDateSource(url);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Callable<Void>> callables = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            callables.add(() -> {
                queryConnection(dateSource);
                return null;
            });

            if (i == 10) {
                callables.add(() -> {

                    HikariConfigMXBean hikariConfigMXBean = dateSource.getHikariConfigMXBean();
                    HikariPoolMXBean hikariPoolMXBean = dateSource.getHikariPoolMXBean();
                    hikariPoolMXBean.suspendPool();
//                    hikariPoolMXBean.softEvictConnections();
                    log.info("suspendPool");
                    TimeUnit.SECONDS.sleep(10);
                    log.info("resumePool");
                    hikariPoolMXBean.resumePool();

                    return null;
                });
            }
        }

        try {
            List<Future<Void>> futures = executorService.invokeAll(callables);
            for (Future<Void> future : futures) {
                try {
                    future.get();
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

    private static void queryConnection(DataSource dateSource) throws SQLException {
        Connection con = dateSource.getConnection();

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT count(*) as connection_count FROM pg_stat_activity where backend_type in ('client backend')");
        while (rs.next()) {
            long connectionCount = rs.getLong("connection_count");
            log.info("connection count: {}", connectionCount);
        }
        rs.close();
        st.close();

        con.close();
    }

    private static HikariDataSource createDateSource(String url) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setAllowPoolSuspension(true);

        return new HikariDataSource(config);
    }
}