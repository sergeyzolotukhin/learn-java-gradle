package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * https://h2database.com/html/tutorial.html#csv
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        var url = "jdbc:h2:mem:";

        try (var con = DriverManager.getConnection(url)) {
             var stm = con.createStatement();

             stm.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255)) " +
                     "AS SELECT * FROM CSVREAD('D:/projects-java/_learn_framework/patterns/library/lib-h2/src/main/resources/test.csv')");

            var rs = stm.executeQuery("SELECT * FROM TEST");
            while (rs.next()) {
                log.info("{} -> {}", rs.getInt(1), rs.getString(2));
            }

            stm.execute("CALL CSVWRITE('library/lib-h2/build/test_wr.csv', 'SELECT * FROM TEST')");
        } catch (SQLException e) {
            log.error("Can not open connection", e);
        }
    }
}