/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate;

import java.util.List;
import java.util.function.Consumer;

import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.CacheStoreMode;
import jakarta.persistence.PessimisticLockScope;
import org.hibernate.graph.RootGraph;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;
import org.hibernate.stat.SessionStatistics;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;

/**
 * The main runtime interface between a Java application and Hibernate. Represents the
 * notion of a <em>persistence context</em>, a set of managed entity instances associated
 * with a logical transaction.
 * <p>
 * The lifecycle of a {@code Session} is bounded by the beginning and end of the logical
 * transaction. But a long logical transaction might span several database transactions.
 * <p>
 * The primary purpose of the {@code Session} is to offer create, read, and delete
 * operations for instances of mapped entity classes. An instance may be in one of three
 * states with respect to a given open session:
 * <ul>
 * <li><em>transient:</em> never persistent, and not associated with the {@code Session},
 * <li><em>persistent:</em> currently associated with the {@code Session}, or
 * <li><em>detached:</em> previously persistent, but not currently associated with the
 *     {@code Session}.
 * </ul>
 * <p>
 * At any given time, an instance may be associated with at most one open session.
 * <p>
 * Any instance returned by {@link #get(Class, Object)} or by a query is persistent.
 * <p>
 * A transient instance may be made persistent by calling {@link #persist(Object)}.
 * A persistent instance may be made detached by calling {@link #detach(Object)}.
 * A persistent instance may be marked for removal, and eventually made transient, by
 * calling {@link #remove(Object)}.
 * <p>
 * Persistent instances are held in a managed state by the persistence context. Any
 * change to the state of a persistent instance is automatically detected and eventually
 * flushed to the database. This process of automatic change detection is called
 * <em>dirty checking</em> and can be expensive in some circumstances. Dirty checking
 * may be disabled by marking an entity as read-only using
 * {@link #setReadOnly(Object, boolean)} or simply by {@linkplain #detach(Object) evicting}
 * it from the persistence context. A session may be set to load entities as read-only
 * {@linkplain #setDefaultReadOnly(boolean) by default}, or this may be controlled at the
 * {@linkplain Query#setReadOnly(boolean) query level}.
 * <p>
 * The state of a transient or detached instance may be made persistent by copying it to
 * a persistent instance using {@link #merge(Object)}. All older operations which moved a
 * detached instance to the persistent state are now deprecated, and clients should now
 * migrate to the use of {@code merge()}.
 * <p>
 * From {@linkplain FlushMode time to time}, a {@linkplain #flush() flush operation} is
 * triggered, and the session synchronizes state held in memory with persistent state
 * held in the database by executing SQL {@code insert}, {@code update}, and {@code delete}
 * statements. Note that SQL statements are often not executed synchronously by the methods
 * of the {@code Session} interface. If synchronous execution of SQL is desired, the
 * {@link StatelessSession} allows this.
 * <p>
 * A persistence context holds hard references to all its entities and prevents them
 * from being garbage collected. Therefore, a {@code Session} is a short-lived object,
 * and must be discarded as soon as a logical transaction ends. In extreme cases,
 * {@link #clear()} and {@link #detach(Object)} may be used to control memory usage.
 * However, for processes which read many entities, a {@link StatelessSession} should
 * be used.
 * <p>
 * A session might be associated with a container-managed JTA transaction, or it might be
 * in control of its own <em>resource-local</em> database transaction. In the case of a
 * resource-local transaction, the client must demarcate the beginning and end of the
 * transaction using a {@link Transaction}. A typical resource-local transaction should
 * use the following idiom:
 * <pre>
 * Session session = factory.openSession();
 * Transaction tx = null;
 * try {
 *     tx = session.beginTransaction();
 *     //do some work
 *     ...
 *     tx.commit();
 * }
 * catch (Exception e) {
 *     if (tx!=null) tx.rollback();
 *     throw e;
 * }
 * finally {
 *     session.close();
 * }
 * </pre>
 * <p>
 * It's crucially important to appreciate the following restrictions and why they exist:
 * <ul>
 * <li>If the {@code Session} throws an exception, the current transaction must be rolled
 *     back and the session must be discarded. The internal state of the {@code Session}
 *     cannot be expected to be consistent with the database after the exception occurs.
 * <li>At the end of a logical transaction, the session must be explicitly {@linkplain
 *     #close() destroyed}, so that all JDBC resources may be released.
 * <li>A {@code Session} is never thread-safe. It contains various different sorts of
 *     fragile mutable state. Each thread or transaction must obtain its own dedicated
 *     instance from the {@link SessionFactory}.
 * </ul>
 * <p>
 * An easy way to be sure that session and transaction management is being done correctly
 * is to {@linkplain SessionFactory#inTransaction(Consumer) let the factory do it}:
 * <pre>
 * sessionFactory.inTransaction(session -&gt; {
 *     //do the work
 *     ...
 * });
 * </pre>
 * <p>
 * A session may be used to {@linkplain #doWork(Work) execute JDBC work} using its JDBC
 * connection and transaction:
 * <pre>
 * session.doWork(connection -&gt; {
 *     try ( PreparedStatement ps = connection.prepareStatement( " ... " ) ) {
 *         ps.execute();
 *     }
 * });
 * </pre>
 * <p>
 * A {@code Session} instance is serializable if its entities are serializable.
 * <p>
 * Every {@code Session} is a JPA {@link EntityManager}. Furthermore, when Hibernate is
 * acting as the JPA persistence provider, the method {@link EntityManager#unwrap(Class)}
 * may be used to obtain the underlying {@code Session}.
 * <p>
 * Hibernate, unlike JPA, allows a persistence unit where an entity class is mapped multiple
 * times, with different entity names, usually to different tables. In this case, the session
 * needs a way to identify the entity name of a given instance of the entity class. Therefore,
 * some operations of this interface, including operations inherited from {@code EntityManager},
 * are overloaded with a form that accepts an explicit entity name along with the instance. An
 * alternative solution to this problem is to provide an {@link EntityNameResolver}.
 *
 * @see SessionFactory
 *
 * @author Gavin King
 * @author Steve Ebersole
 */
public interface Session extends SharedSessionContract, EntityManager {

	/**
	 * Return the persistent instance of the given entity class with the given identifier,
	 * or null if there is no such persistent instance.
	 * If the instance is already associated with the session, return that instance.
	 * This method never returns an uninitialized instance.
	 * <p>
	 * This operation is very similar to {@link #find(Class, Object)}.
	 * <p>
	 * This operation requests {@link LockMode#NONE}, that is, no lock, allowing the object
	 * to be retrieved from the cache without the cost of database access. However, if it is
	 * necessary to read the state from the database, the object will be returned with the
	 * lock mode {@link LockMode#READ}.
	 * <p>
	 *
	 * To bypass the second-level cache, and ensure that the state is read from the database, either:
	 * <ul>
	 * <li>call {@link #get(Class, Object, LockMode)} with the explicit lock mode {@link LockMode#READ}, or
	 * <li>{@linkplain #setCacheMode(CacheMode) set the cache mode} to {@link CacheMode#IGNORE} before calling this method.
	 * </ul>
	 *
	 * @param entityType the entity type
	 * @param id an identifier
	 *
	 * @return a persistent instance or null
	 */
	<T> T get(Class<T> entityType, Object id);
	<T> T get(Class<T> entityType, Object id, LockMode lockMode);
	<T> T get(Class<T> entityType, Object id, LockOptions lockOptions);
	Object get(String entityName, Object id);
	Object get(String entityName, Object id, LockMode lockMode);
	Object get(String entityName, Object id, LockOptions lockOptions);

	/**
	 * Return a reference to the persistent instance with the given class and identifier,
	 * making the assumption that the instance is still persistent in the database.
	 *
	 * This method never results in access to the underlying data store, and thus might return
	 * a proxy that is initialized on-demand, when a non-identifier method is accessed.
	 * <p>
	 * Note that {@link Hibernate#createDetachedProxy(SessionFactory, Class, Object)}
	 * may be used to obtain a <em>detached</em> reference.
	 *
	 * @param entityType the entity type
	 * @param id the identifier of a persistent instance that exists in the database
	 *
	 * @return the persistent instance or proxy
	 */
	<T> T getReference(Class<T> entityType, Object id);
	Object getReference(String entityName, Object id);
	<T> T getReference(T object);

	/**
	 * Return the persistent instance of the given entity class with the given identifier,
	 * making the assumption that the instance exists in the database. This method might
	 * return a proxied instance that is initialized on-demand, when a non-identifier method
	 * is accessed.
	 * <p>
	 * You should not use this method to determine if an instance exists in the database
	 * (use {@code get()} instead). Use this only to retrieve an instance that you assume
	 * exists, where non-existence would be an actual error.
	 * <p>
	 * This operation is very similar to {@link #getReference(Class, Object)}.
	 *
	 * @param theClass a persistent class
	 * @param id a valid identifier of an existing persistent instance of the class
	 *
	 * @return the persistent instance or proxy
	 *
	 * @deprecated use {@link #getReference(Class, Object)}
	 */
	<T> T load(Class<T> theClass, Object id);
	<T> T load(Class<T> theClass, Object id, LockMode lockMode);
	<T> T load(Class<T> theClass, Object id, LockOptions lockOptions);
	Object load(String entityName, Object id, LockMode lockMode);
	Object load(String entityName, Object id, LockOptions lockOptions);
	Object load(String entityName, Object id);
	void load(Object object, Object id);

	/**
	 * Create an {@link IdentifierLoadAccess} instance to retrieve an instance of the given
	 * entity type by its primary key.
	 *
	 * @param entityClass the entity type to be retrieved
	 *
	 * @return an instance of {@link IdentifierLoadAccess} for executing the lookup
	 *
	 * @throws HibernateException If the given class does not resolve as a mapped entity
	 */
	<T> IdentifierLoadAccess<T> byId(Class<T> entityClass);
	<T> IdentifierLoadAccess<T> byId(String entityName);
	<T> MultiIdentifierLoadAccess<T> byMultipleIds(Class<T> entityClass);
	<T> MultiIdentifierLoadAccess<T> byMultipleIds(String entityName);

	/**
	 * Create a {@link NaturalIdLoadAccess} instance to retrieve an instance of the given
	 * entity type by its {@linkplain org.hibernate.annotations.NaturalId natural id},
	 * which may be a composite natural id. The entity must have at least one attribute
	 * annotated {@link org.hibernate.annotations.NaturalId}.
	 *
	 * @param entityClass the entity type to be retrieved
	 *
	 * @return an instance of {@link NaturalIdLoadAccess} for executing the lookup
	 *
	 * @throws HibernateException If the given class does not resolve as a mapped entity,
	 *                            or if the entity does not declare a natural id
	 */
	<T> NaturalIdLoadAccess<T> byNaturalId(Class<T> entityClass);
	<T> NaturalIdLoadAccess<T> byNaturalId(String entityName);
	<T> SimpleNaturalIdLoadAccess<T> bySimpleNaturalId(Class<T> entityClass);
	<T> SimpleNaturalIdLoadAccess<T> bySimpleNaturalId(String entityName);
	<T> NaturalIdMultiLoadAccess<T> byMultipleNaturalId(Class<T> entityClass);
	<T> NaturalIdMultiLoadAccess<T> byMultipleNaturalId(String entityName);

	// ================================================================================================================

	/**
	 * Make a transient instance persistent and mark it for later insertion in the
	 * database. This operation cascades to associated instances if the association
	 * is mapped with {@link jakarta.persistence.CascadeType#PERSIST}.
	 * <p>
	 * For an entity with a {@linkplain jakarta.persistence.GeneratedValue generated}
	 * id, {@code persist()} ultimately results in generation of an identifier for
	 * the given instance. But this may happen asynchronously, when the session is
	 * {@linkplain #flush() flushed}, depending on the identifier generation strategy.
	 *
	 * @param object a transient instance to be made persistent
	 */
	void persist(Object object);
	void persist(String entityName, Object object);
	Object save(Object object);
	Object save(String entityName, Object object);

	/**
	 * Copy the state of the given object onto the persistent object with the same
	 * identifier. If there is no persistent instance currently associated with
	 * the session, it will be loaded. Return the persistent instance. If the
	 * given instance is unsaved, save a copy and return it as a newly persistent
	 * instance. The given instance does not become associated with the session.
	 * This operation cascades to associated instances if the association is mapped
	 * with {@link jakarta.persistence.CascadeType#MERGE}.
	 *
	 * @param object a detached instance with state to be copied
	 *
	 * @return an updated persistent instance
	 */
	<T> T merge(T object);
	<T> T merge(String entityName, T object);
	void update(Object object);
	void update(String entityName, Object object);
	void saveOrUpdate(Object object);
	void saveOrUpdate(String entityName, Object object);



	/**
	 * Return the identifier value of the given entity associated with this session.
	 * An exception is thrown if the given entity instance is transient or detached
	 * in relation to this session.
	 *
	 * @param object a persistent instance associated with this session
	 *
	 * @return the identifier
	 *
	 * @throws TransientObjectException if the instance is transient or associated with
	 * a different session
	 */
	Object getIdentifier(Object object);

	void flush();
	void setFlushMode(FlushModeType flushMode);
	void setHibernateFlushMode(FlushMode flushMode);
	FlushModeType getFlushMode();
	FlushMode getHibernateFlushMode();
	void setCacheMode(CacheMode cacheMode);
	CacheMode getCacheMode();
	CacheStoreMode getCacheStoreMode();
	CacheRetrieveMode getCacheRetrieveMode();
	void setCacheStoreMode(CacheStoreMode cacheStoreMode);
	void setCacheRetrieveMode(CacheRetrieveMode cacheRetrieveMode);
	int getFetchBatchSize();
	void setFetchBatchSize(int batchSize);
	boolean isSubselectFetchingEnabled();
	void setSubselectFetchingEnabled(boolean enabled);
	SessionFactory getSessionFactory();
	void cancelQuery();
	boolean isDirty();
	boolean isDefaultReadOnly();
	void setDefaultReadOnly(boolean readOnly);
	void lock(Object object, LockMode lockMode);
	void lock(Object object, LockOptions lockOptions);
	void lock(String entityName, Object object, LockMode lockMode);
	LockRequest buildLockRequest(LockOptions lockOptions);

	boolean contains(String entityName, Object object);
	void detach(Object object);
	void evict(Object object);

	/**
	 * Persist the state of the given detached instance, reusing the current
	 * identifier value.  This operation cascades to associated instances if
	 * the association is mapped with
	 * {@link org.hibernate.annotations.CascadeType#REPLICATE}.
	 *
	 * @param object a detached instance of a persistent class
	 * @param replicationMode the replication mode to use
	 *
	 * @deprecated With no real replacement
	 */
	@Deprecated( since = "6.0" )
	void replicate(Object object, ReplicationMode replicationMode);
	void replicate(String entityName, Object object, ReplicationMode replicationMode) ;

	/**
	 * Remove a persistent instance from the datastore. The argument may be
	 * an instance associated with the receiving {@code Session} or a transient
	 * instance with an identifier associated with existing persistent state.
	 * This operation cascades to associated instances if the association is
	 * mapped with {@link jakarta.persistence.CascadeType#REMOVE}.
	 *
	 * @param object the instance to be removed
	 *
	 * @deprecated use {@link #remove(Object)}
	 */
	@Deprecated(since = "6.0")
	void delete(Object object);
	void delete(String entityName, Object object);

	/**
	 * Reread the state of the given managed instance associated with this session
	 * from the underlying database. This may be useful:
	 * <ul>
	 * <li>when a database trigger alters the object state upon insert or update,
	 * <li>after {@linkplain #createMutationQuery(String) executing} any HQL update
	 *     or delete statement,
	 * <li>after {@linkplain #createNativeMutationQuery(String) executing} a native
	 *     SQL statement, or
	 * <li>after inserting a {@link java.sql.Blob} or {@link java.sql.Clob}.
	 * </ul>
	 * <p>
	 * This operation cascades to associated instances if the association is mapped
	 * with {@link jakarta.persistence.CascadeType#REFRESH}.
	 * <p>
	 * This operation requests {@link LockMode#READ}. To obtain a stronger lock,
	 * call {@link #refresh(Object, LockMode)}.
	 *
	 * @param object a persistent or detached instance
	 */
	void refresh(Object object);
	void refresh(String entityName, Object object);
	void refresh(Object object, LockMode lockMode);
	void refresh(Object object, LockOptions lockOptions);
	void refresh(String entityName, Object object, LockOptions lockOptions);

	/**
	 * Mark a persistence instance associated with this session for removal from
	 * the underlying database. Ths operation cascades to associated instances if
	 * the association is mapped {@link jakarta.persistence.CascadeType#REMOVE}.
	 *
	 * @param object the managed persistent instance to remove
	 */
	void remove(Object object);
	LockMode getCurrentLockMode(Object object);
	void clear();
	String getEntityName(Object object);





	// ================================================================================================================

	Filter enableFilter(String filterName);
	Filter getEnabledFilter(String filterName);
	void disableFilter(String filterName);
	SessionStatistics getStatistics();
	boolean isReadOnly(Object entityOrProxy);
	void setReadOnly(Object entityOrProxy, boolean readOnly);
	boolean isFetchProfileEnabled(String name) throws UnknownProfileException;
	void enableFetchProfile(String name) throws UnknownProfileException;
	void disableFetchProfile(String name) throws UnknownProfileException;
	LobHelper getLobHelper();
	SharedSessionBuilder sessionWithOptions();



	/**
	 * Add one or more listeners to the Session
	 *
	 * @param listeners the listener(s) to add
	 */
	void addEventListeners(SessionEventListener... listeners);

	<T> RootGraph<T> createEntityGraph(Class<T> rootType);
	RootGraph<?> createEntityGraph(String graphName);
	RootGraph<?> getEntityGraph(String graphName);
	<T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass);
	<R> Query<R> createQuery(String queryString, Class<R> resultClass);
	Query createQuery(String queryString);
	<R> Query<R> createNamedQuery(String name, Class<R> resultClass);
	Query createNamedQuery(String name);
	<R> Query<R> createQuery(CriteriaQuery<R> criteriaQuery);
	Query createQuery(CriteriaDelete deleteQuery);
	Query createQuery(CriteriaUpdate updateQuery);
}
