package ua.in.sz.h2database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(H2CreateExtension.class)
class ApplicationTest {

	@Test
	void main(Connection connection) {
		Application.main(connection);
	}
}