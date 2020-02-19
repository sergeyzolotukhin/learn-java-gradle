package ua.in.sz.jdbc;

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

			InputStream is = Application.class.getClassLoader().getResourceAsStream("application.properties");
			Objects.requireNonNull(is, "Properties file not found");

			Properties prop = new Properties();
			prop.load(is);

			String jdbcUrl = prop.getProperty("jdbc.url");
			String username = prop.getProperty("jdbc.username");
			String password = prop.getProperty("jdbc.password");

			Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

			log.info("Executing statements");

			Statement statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT employee_name FROM employee");
			while (resultSet.next()) {
				String name = resultSet.getString("employee_name");

				log.info("Employer name: [{}]", name);
			}

			log.info("Executed statements");

			resultSet.close();
			statement.close();
			connection.close();

			log.info("Disconnecting to database");
		} catch (SQLException | IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
