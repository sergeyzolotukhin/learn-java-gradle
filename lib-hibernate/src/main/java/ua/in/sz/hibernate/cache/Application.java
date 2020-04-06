package ua.in.sz.hibernate.cache;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.hibernate.cache.impl.Schedule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Slf4j
public class Application {
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		Schedule schedule = Schedule.builder().name("Schedule 1").build();

		em.persist(schedule);
		em.getTransaction().commit();
	}
}