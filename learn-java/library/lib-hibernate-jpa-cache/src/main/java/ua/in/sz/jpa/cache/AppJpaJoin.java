package ua.in.sz.jpa.cache;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.SetJoin;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.jpa.cache.impl.Schedule;
import ua.in.sz.jpa.cache.impl.Workspace;
import ua.in.sz.jpa.cache.impl.Workspace_;

import java.util.List;

@Slf4j
public class AppJpaJoin {
	public static void main(String[] args) {
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("PERSISTENCE")) {
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
			log.info("query");
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Schedule> cq = cb.createQuery(Schedule.class);
			Root<Workspace> root = cq.from(Workspace.class);
			SetJoin<Workspace, Schedule> s = root.join(Workspace_.schedules, JoinType.LEFT);
			CriteriaQuery<Schedule> select = cq.select(s);
			List<Schedule> schedules = em.createQuery(select).getResultList();

			log.info("{}", schedules);
		}
	}
}