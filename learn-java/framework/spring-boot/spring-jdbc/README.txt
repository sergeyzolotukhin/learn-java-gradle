https://github.com/rahamath18/spring-boot-jdbc-example

public abstract class TransactionSynchronizationManager

	private static final ThreadLocal<Map<Object, Object>> resources =
			new NamedThreadLocal<>("Transactional resources");

org.springframework.transaction.support.TransactionSynchronizationManager
    -> bindResource
        -> DataSource <-> ConnectionHolder
            -> SimpleConnectionHandle -> Connection