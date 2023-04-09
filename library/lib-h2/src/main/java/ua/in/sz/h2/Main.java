package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

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

            StopWatch sw = StopWatch.createStarted();

            stm.execute("CREATE TABLE TEST(ID INT PRIMARY KEY, NAME VARCHAR(255)) "
//                    +
//                    "AS SELECT * FROM CSVREAD('D:/projects-java/_learn_framework/patterns/library/lib-h2/src/main/resources/test.csv')"
            );

//            String sql = "insert into TEST (ID, NAME) values (?, ?)";
//            con.setAutoCommit(false);
//            try (var ps = con.prepareStatement(sql)) {
//                for (int i = 3; i < 100_000; i++) {
//                    ps.setInt(1, i);
//                    ps.setString(2, String.format("Name_%d", i));
//                    ps.addBatch();
//                }
//                ps.executeBatch();
//                ps.clearBatch();
//            }
//            con.commit();

            // insert one by one
            String sql = "insert into TEST (ID, NAME) values (?, ?)";
            con.setAutoCommit(false);
            for (int i = 3; i < 100_000; i++) {
                try (var ps = con.prepareStatement(sql)) {
                    ps.setInt(1, i);
                    ps.setString(2, String.format("Name_%d", i));
                    ps.execute();
                }
            }
            con.commit();

            // batch insert:    00:00:00.274
            // csv load:        00:00:00.217
            // insert:          00:00:00.279

            log.info("Database creation took {}", sw);

            var rs = stm.executeQuery("SELECT count(*) FROM TEST");
            while (rs.next()) {
                log.info("CountL {}", rs.getLong(1));
            }

//            stm.execute("CALL CSVWRITE('library/lib-h2/build/test_wr.csv', 'SELECT * FROM TEST')");
        } catch (SQLException e) {
            log.error("Can not open connection", e);
        }
    }
}