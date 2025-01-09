package ua.in.sz.hibernate.persistence.unit;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import ua.in.sz.hibernate.persistence.unit.entities.PersistenceUnitSchedule;
import ua.in.sz.hibernate.persistence.unit.entities.PersistenceUnitWorkspace;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AppPersistenceUnitJpa {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
		EntityManager em = emf.createEntityManager();

		// model
		PersistenceUnitWorkspace workspace = PersistenceUnitWorkspace.builder().name("Workspace 1").build();
		workspace.add(PersistenceUnitSchedule.builder().name("Schedule 1").build());
		workspace.add(PersistenceUnitSchedule.builder().name("Schedule 2").build());

		log.info("persist");
		em.getTransaction().begin();
		em.persist(workspace);
		em.getTransaction().commit();
		em.clear();

		log.info("find");
		PersistenceUnitWorkspace w1 = em.find(PersistenceUnitWorkspace.class, workspace.getId());
		em.detach(w1);

		String s1Names = w1.getSchedules().stream().map(PersistenceUnitSchedule::getName).collect(Collectors.joining(","));

		log.info("Schedules [{}] in workspace [{}] ", s1Names, w1.getName());

		// JPA2 Meta model & criteria API
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PersistenceUnitSchedule> cq = cb.createQuery(PersistenceUnitSchedule.class);
		Root<PersistenceUnitSchedule> from = cq.from(PersistenceUnitSchedule.class);
//		cq.where(cb.equal(from.get(Schedule_.name), "Schedule 2"));
		CriteriaQuery<PersistenceUnitSchedule> select = cq.select(from);
		List<PersistenceUnitSchedule> schedules = em.createQuery(select).getResultList();

		for (PersistenceUnitSchedule schedule : schedules) {
			log.info("Schedule: [{}]", schedule.getName());
		}

		TypedQuery<PersistenceUnitSchedule> query = em.createQuery("select m from PersistenceUnitSchedule m", PersistenceUnitSchedule.class);
		List<PersistenceUnitSchedule> list = query.getResultList();

		Session session = em.unwrap(Session.class);
		Query<PersistenceUnitSchedule> q1 = session.createQuery("select m from PersistenceUnitSchedule m", PersistenceUnitSchedule.class);
		q1.setCacheRegion("f");
		q1.setCacheable(true);
		List<PersistenceUnitSchedule> l1 = q1.list();

		NativeQuery<PersistenceUnitSchedule> nq1 = session.createNativeQuery("select * from SCHEDULE", PersistenceUnitSchedule.class);
		nq1.addSynchronizedQuerySpace("f");
		nq1.setCacheable(true);
		nq1.setCacheRegion("f");
		List<PersistenceUnitSchedule> nl1 = nq1.list();

	}
}