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

@Slf4j
public class JdbcBatchApplication {

	public static void main(String[] args) {
		Connection connection;

		try {
			log.info("Connecting to database");

			connection = connectionToDatabase();
			deleteEmployerName(connection);
			connection.close();

			connection = connectionToDatabase();
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
//		log.info("Inserting employer");

		connection.setAutoCommit(false);

		long b = System.currentTimeMillis();
		PreparedStatement statement = connection.prepareStatement(
				"insert into EMPLOYEE(EMPLOYEE_ID, EMPLOYEE_NAME) values(?, ?)");

		for (int i = 0; i < 200_000; i++) {
			statement.setString(1, String.format("id_%d", i));
			statement.setString(2, String.format("Serhij Zolotukhin %d", i));
			statement.addBatch();

			if (i % 1000 == 0) {
				statement.executeBatch();
			}
		}

		statement.executeBatch();

		statement.close();
		connection.commit();

		long a = System.currentTimeMillis();

		log.info("Inserted employer. Elapsed time: {} ms", a - b);
	}

	@SneakyThrows
	private static void deleteEmployerName(Connection connection) {
//		log.info("Deleting employer");

		connection.setAutoCommit(false);

		Statement statement = connection.createStatement();
		statement.executeUpdate("delete from EMPLOYEE");
		statement.close();

		connection.commit();

		log.info("Deleted employer");
	}

	@SneakyThrows
	private static void queryEmployerName(Connection connection) {
//		log.info("Querying employer name");

		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("SELECT count(*) as employee_count FROM employee");
		while (resultSet.next()) {
			Long count = resultSet.getLong("employee_count");

			log.info("Employer count: [{}]", count);
		}

//		log.info("Queried employer name");

		resultSet.close();
		statement.close();
	}

	@SneakyThrows
	private static Connection connectionToDatabase() {
		InputStream is = JdbcBatchApplication.class.getClassLoader().getResourceAsStream("application.properties");
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
