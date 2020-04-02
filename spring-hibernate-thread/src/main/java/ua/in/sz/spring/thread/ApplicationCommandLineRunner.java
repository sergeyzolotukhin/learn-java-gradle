package ua.in.sz.spring.thread;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.spring.thread.entity.ScheduleEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Getter
@Component
@RequiredArgsConstructor
public class ApplicationCommandLineRunner implements CommandLineRunner {

	private final EntityManager entityManager;

	@Override
	@SneakyThrows
	public void run(String...args) {
		log.info("Start application");

		ExecutorService executor = Executors.newFixedThreadPool(10);

		List<Callable<Void>> tasks = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			tasks.add(this::runQuery);
		}

		List<Future<Void>> futures = executor.invokeAll(tasks);

		for (Future<Void> future : futures) {
			future.get();
		}

		executor.shutdown();

		log.info("End application");
	}

	private Void runQuery() {
		long count = countEntity();
		log.info("Entity count {}", count);
		return null;
	}

	private long countEntity() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		CriteriaQuery<Long> query = cq.select(cb.count(cq.from(ScheduleEntity.class)));

		return getEntityManager().createQuery(query).getSingleResult();
	}
}