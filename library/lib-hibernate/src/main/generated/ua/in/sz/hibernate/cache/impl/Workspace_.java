package ua.in.sz.hibernate.cache.impl;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Workspace.class)
public abstract class Workspace_ {

	public static volatile SetAttribute<Workspace, Schedule> schedules;
	public static volatile SingularAttribute<Workspace, String> name;
	public static volatile SingularAttribute<Workspace, Long> id;

	public static final String SCHEDULES = "schedules";
	public static final String NAME = "name";
	public static final String ID = "id";

}

