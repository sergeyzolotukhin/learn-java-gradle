package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.hibernate.cache.impl.Schedule;
import ua.in.sz.hibernate.cache.impl.Workspace;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Slf4j
public class Application {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
		EntityManager em = emf.createEntityManager();

		Schedule schedule = Schedule.builder().name("Schedule 1").build();
		Workspace workspace = Workspace.builder().name("Workspace 1").build();

		em.getTransaction().begin();
		em.persist(schedule);
		em.persist(workspace);
		em.getTransaction().commit();

		Schedule result = em.find(Schedule.class, schedule.getId());
		em.detach(result);

		Workspace w1 = em.find(Workspace.class, workspace.getId());
		em.detach(w1);

		log.info("Schedule [{}] from workspace [{}] ", schedule.getName(), w1.getName());
	}
}