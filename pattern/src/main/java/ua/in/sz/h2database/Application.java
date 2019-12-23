package ua.in.sz.h2database;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.h2.util.Profiler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Slf4j
public class Application {
	@SneakyThrows
	public static void main(Connection con) {

		Profiler prof = new Profiler();
		prof.startCollecting();

		log.info("connection 1");

//		Connection con = DriverManager.getConnection("jdbc:h2:mem:database-1;TRACE_LEVEL_SYSTEM_OUT=3");
//		Connection con = DriverManager.getConnection("jdbc:h2:mem:database-1");

		for (int i = 0; i < 2; i++) {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT 1+1");

			if (rs.next()) {
				log.info("Result: {}", rs.getInt(1));
			}

			rs.close();
			stm.close();
		}
//		con.close();

		log.info("connection 2");

//		Connection con2 = DriverManager.getConnection("jdbc:h2:mem:database-2;TRACE_LEVEL_SYSTEM_OUT=3");
//		Connection con2 = DriverManager.getConnection("jdbc:h2:mem:database-2");

		for (int i = 0; i < 2; i++) {
			Statement stm2 = con.createStatement();
			ResultSet rs2 = stm2.executeQuery("SELECT 1+1");

			if (rs2.next()) {
				log.info("Result: {}", rs2.getInt(1));
			}

			rs2.close();
			stm2.close();
		}
//		con.close();

		log.info("end");

		prof.stopCollecting();
		log.trace("Statistic: {}", prof.getTop(3));

		// http://www.h2database.com/html/performance.html
		// https://github.com/zapodot/embedded-db-junit
	}
}