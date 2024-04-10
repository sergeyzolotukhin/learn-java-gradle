/*
 * Copyright (c) 2008, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */

// Contributors:
//     Linda DeMichiel - 2.1
//     Linda DeMichiel - 2.0


package jakarta.persistence;

import java.util.Map;
import java.util.List;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.CriteriaDelete;

/**
 * Interface used to interact with the persistence context.
 *
 * <p> An <code>EntityManager</code> instance is associated with
 * a persistence context. A persistence context is a set of entity
 * instances in which for any persistent entity identity there is
 * a unique entity instance. Within the persistence context, the
 * entity instances and their lifecycle are managed.
 * The <code>EntityManager</code> API is used
 * to create and remove persistent entity instances, to find entities
 * by their primary key, and to query over entities.
 *
 * <p> The set of entities that can be managed by a given
 * <code>EntityManager</code> instance is defined by a persistence
 * unit. A persistence unit defines the set of all classes that are
 * related or grouped by the application, and which must be
 * colocated in their mapping to a single database.
 *
 * @see Query
 * @see TypedQuery
 * @see CriteriaQuery
 * @see PersistenceContext
 * @see StoredProcedureQuery
 *
 * @since 1.0
 */
public interface EntityManager extends AutoCloseable {

    /**
     * Find by primary key.
     * Search for an entity of the specified class and primary key.
     * If the entity instance is contained in the persistence context, it is returned from there.
     * @param entityClass  entity class
     * @param primaryKey  primary key
     * @return the found entity instance or null if the entity does not exist
     */
    public <T> T find(Class<T> entityClass, Object primaryKey);
    public <T> T find(Class<T> entityClass, Object primaryKey, Map<String, Object> properties);
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode);
    public <T> T find(Class<T> entityClass, Object primaryKey, LockModeType lockMode, Map<String, Object> properties);

    /**
     * Get an instance, whose state may be lazily fetched.
     * If the requested instance does not exist in the database, the <code>EntityNotFoundException</code> is thrown.
     *
     * The application should not expect that the instance state will
     * be available upon detachment, unless it was accessed by the
     * application while the entity manager was open.
     *
     * @param entityClass  entity class
     * @param primaryKey  primary key
     * @return the found entity instance
     * @throws IllegalArgumentException if the first argument does
     *         not denote an entity type or the second argument is
     *         not a valid type for that entity's primary key or
     *         is null
     * @throws EntityNotFoundException if the entity state
     *         cannot be accessed
     */
    public <T> T getReference(Class<T> entityClass, Object primaryKey);

    /**
     * Refresh the state of the instance from the database, overwriting changes made to the entity, if any.
     * @param entity  entity instance
     *
     * @throws IllegalArgumentException if the instance is not an entity or the entity is not managed
     * @throws TransactionRequiredException if there is no transaction when invoked on a container-managed
     *         entity manager of type <code>PersistenceContextType.TRANSACTION</code>
     * @throws EntityNotFoundException if the entity no longer exists in the database
     */
    public void refresh(Object entity);
    public void refresh(Object entity, Map<String, Object> properties);
    public void refresh(Object entity, LockModeType lockMode);
    public void refresh(Object entity, LockModeType lockMode, Map<String, Object> properties);

    // =============================================================================================================

    public boolean contains(Object entity);

    /**
     * Make an instance managed and persistent.
     * @param entity  entity instance
     * @throws EntityExistsException if the entity already exists.
     * (If the entity already exists, the <code>EntityExistsException</code> may
     * be thrown when the persist operation is invoked, or the
     * <code>EntityExistsException</code> or another <code>PersistenceException</code> may be
     * thrown at flush or commit time.)
     * @throws IllegalArgumentException if the instance is not an
     *         entity
     * @throws TransactionRequiredException if there is no transaction when
     *         invoked on a container-managed entity manager of that is of type
     *         <code>PersistenceContextType.TRANSACTION</code>
     */
    public void persist(Object entity);
    public <T> T merge(T entity);
    public void remove(Object entity);

    public void clear();
    public void detach(Object entity);
    public void flush();
    public void setFlushMode(FlushModeType flushMode);
    public FlushModeType getFlushMode();
    public void lock(Object entity, LockModeType lockMode);
    public void lock(Object entity, LockModeType lockMode, Map<String, Object> properties);
    public LockModeType getLockMode(Object entity);
    public void setProperty(String propertyName, Object value);
    public Map<String, Object> getProperties();
    public Query createQuery(String qlString);
    public <T> TypedQuery<T> createQuery(CriteriaQuery<T> criteriaQuery);
    public Query createQuery(CriteriaUpdate updateQuery);
    public Query createQuery(CriteriaDelete deleteQuery);
    public <T> TypedQuery<T> createQuery(String qlString, Class<T> resultClass);
    public Query createNamedQuery(String name);
    public <T> TypedQuery<T> createNamedQuery(String name, Class<T> resultClass);
    public Query createNativeQuery(String sqlString);
    public Query createNativeQuery(String sqlString, Class resultClass);
    public Query createNativeQuery(String sqlString, String resultSetMapping);
    public StoredProcedureQuery createNamedStoredProcedureQuery(String name);
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName);
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, Class... resultClasses);
    public StoredProcedureQuery createStoredProcedureQuery(String procedureName, String... resultSetMappings);
    public void joinTransaction();
    public boolean isJoinedToTransaction();
    public <T> T unwrap(Class<T> cls);
    public Object getDelegate();
    public void close();
    public boolean isOpen();
    public EntityTransaction getTransaction();
    public EntityManagerFactory getEntityManagerFactory();
    public CriteriaBuilder getCriteriaBuilder();
    public Metamodel getMetamodel();
    public <T> EntityGraph<T> createEntityGraph(Class<T> rootType);
    public EntityGraph<?> createEntityGraph(String graphName);
    public  EntityGraph<?> getEntityGraph(String graphName);
    public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> entityClass);

}
