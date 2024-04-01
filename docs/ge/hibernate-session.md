#### Get list of entities in the hibernate session
org.hibernate.engine.internal.StatefulPersistenceContext.getEntitiesByKey
https://stackoverflow.com/questions/16460796/hibernate-how-to-get-a-list-of-all-the-objects-currently-in-the-session

#### Get list of collection in the hibernate session

org.hibernate.internal.SessionImpl.getPersistenceContext
org.hibernate.engine.internal.StatefulPersistenceContext.getCollectionEntries

org.hibernate.collection.internal.AbstractPersistentCollection.getRole

#### Get entity type 
org.hibernate.persister.entity.AbstractEntityPersister.getType
org.hibernate.type.EntityType.getReturnedClass