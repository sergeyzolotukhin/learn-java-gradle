package ua.in.sz.jdbc;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

@Slf4j
public class Application {

	public static void main(String[] args) {
		Connection connection;

		try {
			log.info("Connecting to database");

			connection = connectionToDatabase();
			connection.setAutoCommit(false);
			insertEmployerName(connection);
			connection.close();

			connection = connectionToDatabase();
			queryEmployerName(connection);
			connection.close();

			log.info("Disconnecting to database");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@SneakyThrows
	private static void insertEmployerName(Connection connection) {
		log.info("Inserting employer");

		UUID uuid = UUID.randomUUID();

		PreparedStatement statement = connection.prepareStatement(
				"insert into EMPLOYEE(EMPLOYEE_ID, EMPLOYEE_NAME) values(?, ?)");

		statement.setString(1, uuid.toString());
		statement.setString(2, "Serhij Zolotukhin");

		statement.execute();

		statement.close();

		log.info("Inserted employer");
	}

	@SneakyThrows
	private static void queryEmployerName(Connection connection) {
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
}
