package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Transactional
public class HelloWorldDaoImpl implements HelloWorldDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void doTask() {
        try {
            log.info("Start task");

            long count = 0;
            for (int i = 0; i < 1; i++) {
                count = countEmployee();
            }

            log.info(String.format("Employee count: %d", count));

            log.info("End task");
        } catch (Exception e) {
            log.error("Can't do task", e);
        }

    }

    private long countEmployee() throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("select count(*) from employee");
        ) {
            resultSet.next();
            return resultSet.getLong(1);
        }
    }
}
