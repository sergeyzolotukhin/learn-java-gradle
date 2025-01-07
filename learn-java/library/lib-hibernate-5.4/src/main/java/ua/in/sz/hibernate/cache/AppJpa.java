package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ua.in.sz.hibernate.cache.impl.Schedule;
import ua.in.sz.hibernate.cache.impl.Workspace;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AppJpa {
	public static void main(String[] args) {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		try {
			MetadataSources metadataSources = new MetadataSources(registry);
			SessionFactory sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

//			findSchedules(sessionFactory);

			sessionFactory.close();
		} catch (Exception e) {
			log.error("Can't save or load workspace", e);
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}