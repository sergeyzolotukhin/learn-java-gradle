package ua.in.sz.h2database;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Slf4j
public class Application {
	@SneakyThrows
	public static void main(String[] args) {
		log.info("connection 1");

		Connection con = DriverManager.getConnection("jdbc:h2:mem:database-1;TRACE_LEVEL_SYSTEM_OUT=3");
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("SELECT 1+1");

		if (rs.next()) {
			log.info("Result: {}", rs.getInt(1));
		}

		log.info("connection 2");

		Connection con2 = DriverManager.getConnection("jdbc:h2:mem:database-2;TRACE_LEVEL_SYSTEM_OUT=3");
		Statement stm2 = con2.createStatement();
		ResultSet rs2 = stm2.executeQuery("SELECT 1+1");

		if (rs2.next()) {
			log.info("Result: {}", rs2.getInt(1));
		}

		log.info("end");
	}
}