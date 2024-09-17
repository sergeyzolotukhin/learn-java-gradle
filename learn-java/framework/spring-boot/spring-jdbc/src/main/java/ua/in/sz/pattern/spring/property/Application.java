package ua.in.sz.pattern.spring.property;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
@SpringBootApplication
public class Application {
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        DataSource dataSource = dataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);


        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                jdbcTemplate.execute("SELECT 1");
                return null;
            }
        });

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
