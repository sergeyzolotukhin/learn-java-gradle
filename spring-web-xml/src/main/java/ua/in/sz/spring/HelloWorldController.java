package ua.in.sz.spring;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

public class HelloWorldController extends AbstractController {

    private TaskExecutor taskExecutor;
    private DataSource dataSource;

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        logger.info("Get hello request");

        ModelAndView model = new ModelAndView("HelloWorldPage");
        model.addObject("msg", "hello world");

        taskExecutor.execute(this::doTask);

        return model;
    }

    private void doTask() {
        try {
            logger.info("Start task");
            TimeUnit.SECONDS.sleep(20);

            long count = countEmployee();
            logger.info(String.format("Employee count: %d", count));

            logger.info("End task");
        } catch (Exception e) {
            logger.error("Can't do task", e);
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