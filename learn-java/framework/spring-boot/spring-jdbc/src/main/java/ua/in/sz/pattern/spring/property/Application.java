package ua.in.sz.pattern.spring.property;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Configuration
@SpringBootApplication
public class Application {
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws InterruptedException {
        DataSource dataSource = dataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        TransactionTemplate nestedTransactionTemplate = new TransactionTemplate(transactionManager);
        nestedTransactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_MANDATORY);
//        nestedTransactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        nestedTransactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        transactionTemplate.execute(status -> {
            Future<?> future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    nestedTransactionTemplate.execute(s -> {
                        jdbcTemplate.execute("SELECT 1");
                        return null;
                    });
                }
            });

            try {
                future.get(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return null;
        });

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        log.info("End");
    }

    private static DataSource dataSource() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL(JDBC_URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }
}
