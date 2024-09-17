https://github.com/rahamath18/spring-boot-jdbc-example

public abstract class TransactionSynchronizationManager

	private static final ThreadLocal<Map<Object, Object>> resources =
			new NamedThreadLocal<>("Transactional resources");

org.springframework.transaction.support.TransactionSynchronizationManager
    -> bindResource
        -> DataSource <-> ConnectionHolder
            -> SimpleConnectionHandle -> Connection


https://dulajra.medium.com/spring-transaction-management-over-multiple-threads-dzone-java-b36a5bc342e5
https://dzone.com/articles/spring-transaction-management-over-multiple-thread-1
https://dzone.com/articles/mastering-spring-synchronizing-transactional-and-a
https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/transaction.html
