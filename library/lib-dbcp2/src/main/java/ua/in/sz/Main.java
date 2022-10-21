package ua.in.sz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        log.info("start");
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=postgres";

        BasicDataSource dateSource = createDateSource(url);

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        List<Callable<String>> callables = new ArrayList<>();

        for (int i = 0; i < 200; i++) {
            callables.add(() -> queryConnection(dateSource));
        }

        try {
            List<Future<String>> futures = executorService.invokeAll(callables);
            for (Future<String> future : futures) {
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

    private static BasicDataSource createDateSource(String url) {
        // https://commons.apache.org/proper/commons-dbcp/configuration.html
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);

        dataSource.setInitialSize(70);
        dataSource.setMinIdle(70);
        dataSource.setMaxIdle(80);
        dataSource.setMaxTotal(90);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnCreate(true);
//        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);

        return dataSource;
    }
}