package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.hibernate.cache.impl.Schedule;
import ua.in.sz.hibernate.cache.impl.Workspace;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
public class Application {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
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

		log.info("find");
		Workspace w1 = em.find(Workspace.class, workspace.getId());
		em.detach(w1);

		String s1Names = w1.getSchedules().stream().map(Schedule::getName).collect(Collectors.joining(","));

		log.info("Schedules [{}] in workspace [{}] ", s1Names, w1.getName());

		// JPA2 Meta model & criteria API
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Schedule> cq = cb.createQuery(Schedule.class);
		Root<Schedule> from = cq.from(Schedule.class);
		cq.where(cb.equal(from.get("name"), "Schedule 2"));
		CriteriaQuery<Schedule> select = cq.select(from);
		List<Schedule> schedules = em.createQuery(select).getResultList();

		for (Schedule schedule : schedules) {
			log.info("Schedule: [{}]", schedule.getName());
		}
	}
}