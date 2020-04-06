package ua.in.sz.hibernate.cache;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.hibernate.cache.impl.Schedule;
import ua.in.sz.hibernate.cache.impl.Workspace;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class Application {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE");
		EntityManager em = emf.createEntityManager();

		// model
		Schedule schedule1 = Schedule.builder().name("Schedule 1").build();
		Schedule schedule2 = Schedule.builder().name("Schedule 2").build();
		Workspace workspace = Workspace.builder().name("Workspace 1").build();

		schedule1.setWorkspace(workspace);
		schedule2.setWorkspace(workspace);

		Set<Schedule> schedules = Sets.newHashSet();
		schedules.add(schedule1);
		schedules.add(schedule2);
		workspace.setSchedules(schedules);

		// persist
		em.getTransaction().begin();
		em.persist(schedule1);
		em.persist(schedule2);
		em.persist(workspace);
		em.getTransaction().commit();

		// find
		Schedule s1 = em.find(Schedule.class, schedule1.getId());
		Workspace w1 = em.find(Workspace.class, workspace.getId());

		String s1Names = w1.getSchedules().stream().map(Schedule::getName).collect(Collectors.joining(","));

		log.info("Schedules [{}] in workspace [{}] ", s1Names, s1.getWorkspace().getName());

		em.detach(s1);
		em.detach(w1);
	}
}