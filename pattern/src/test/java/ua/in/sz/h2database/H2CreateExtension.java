package ua.in.sz.h2database;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Testcontainers
 *
 * https://www.baeldung.com/junit-5-extensions
 * https://blog.codefx.org/design/architecture/junit-5-extension-model/
 */
@Slf4j
public class H2CreateExtension implements BeforeAllCallback, AfterAllCallback, ParameterResolver {

	private static final ExtensionContext.Namespace H2_NAMESPACE =
			ExtensionContext.Namespace.create(H2CreateExtension.class.getSimpleName());

	private static final String CONNECT = "connect";

	@Override
	public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException
	{
		boolean supported = parameterContext.getParameter().getType().equals(Connection.class);

		if (supported) {
			log.info("Parameter supported: {}", extensionContext.getDisplayName());
		}

		return supported;
	}

	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException
	{
		return extensionContext.getStore(H2_NAMESPACE).get(CONNECT, Connection.class);
	}

	@Override
	public void beforeAll(ExtensionContext context) throws SQLException {
		Connection connect = context.getStore(H2_NAMESPACE).get(CONNECT, Connection.class);
		if (connect == null) {
			connect = DriverManager.getConnection("jdbc:h2:mem:;TRACE_LEVEL_FILE=2;TRACE_LEVEL_SYSTEM_OUT=3");
			context.getStore(H2_NAMESPACE).put(CONNECT, connect);

			log.info("Connection created");
		}
	}

	@Override
	public void afterAll(ExtensionContext context) throws SQLException {
		Connection connect = context.getStore(H2_NAMESPACE).get(CONNECT, Connection.class);
		if (connect != null) {
			connect.close();
			context.getStore(H2_NAMESPACE).remove(CONNECT);

			log.info("Connection destroyed");
		}
	}
}
