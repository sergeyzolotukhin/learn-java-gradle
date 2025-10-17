#### merge a DETACHED into a PERSISTENT  

DefaultMergeEventListener.entityIsDetached
    * Object target = source.get( entityName, clonedIdentifier )
    * cascadeOnMerge( source, persister, entity, copyCache );
    * copyValues( persister, entity, target, source, copyCache );
    * markInterceptorDirty( entity, target )

source - session
entity - DETACHED entity
target - PERSISTENT entity
