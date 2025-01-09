package ua.in.sz.hibernate.cache;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.in.sz.hibernate.cache.impl.Schedule;
import ua.in.sz.hibernate.cache.impl.Workspace;
import ua.in.sz.hibernate.dirty.check.model.DirtyParameter;

import java.util.List;

@Slf4j
public class AppJpaJoin {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE-CACHE");
		EntityManager em = emf.createEntityManager();

		// model
		Workspace workspace = Workspace.builder().name("Workspace 1").build();
		workspace.add(Schedule.builder().name("Schedule 1").build());
		workspace.add(Schedule.builder().name("Schedule 2").build());

		log.info("persist");
		em.getTransaction().begin();
		em.persist(workspace);
		em.getTransaction().commit();
		em.clear();

		// JPA2 Meta model & criteria API
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Schedule> cq = cb.createQuery(Schedule.class);
		Root<Workspace> from = cq.from(Workspace.class);
		from.join("schedules", JoinType.LEFT);
		CriteriaQuery<Schedule> select = cq.select(from.get("schedules"));
		List<Schedule> schedules = em.createQuery(select).getResultList();

		log.info("{}", schedules);

	}
}