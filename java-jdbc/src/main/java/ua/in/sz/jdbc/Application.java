package ua.in.sz.jdbc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

@Slf4j
public class Application {

	public static void main(String[] args) {
		try {
			log.info("Connecting to database");

			Connection connection = connectionToDatabase();

			queryEmploerName(connection);

			connection.close();

			log.info("Disconnecting to database");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@SneakyThrows
	private static Connection connectionToDatabase() {
		InputStream is = Application.class.getClassLoader().getResourceAsStream("application.properties");
		Objects.requireNonNull(is, "Properties file not found");

		Properties prop = new Properties();
		prop.load(is);

		is.close();

		String jdbcUrl = prop.getProperty("jdbc.url");
		String username = prop.getProperty("jdbc.username");
		String password = prop.getProperty("jdbc.password");

		return DriverManager.getConnection(jdbcUrl, username, password);
	}

	@SneakyThrows
	private static void queryEmploerName(Connection connection) {
		log.info("Querying employer name");

		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("SELECT employee_name FROM employee");
		while (resultSet.next()) {
			String name = resultSet.getString("employee_name");

			log.info("Employer name: [{}]", name);
		}

		log.info("Queried employer name");

		resultSet.close();
		statement.close();
	}
}
