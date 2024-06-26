package ua.in.sz.spring.thread;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ua.in.sz.spring.thread.entity.ScheduleEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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

	public static final String MDC_FEATURE = "feature";
	private final EntityManager entityManager;

	@Override
	@SneakyThrows
	public void run(String...args) {
		log.info("Start application");

		ExecutorService executor = Executors.newFixedThreadPool(10);

		List<Callable<Void>> tasks = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			final int no = i;
			tasks.add(() -> runQuery(no));
		}

		List<Future<Void>> futures = executor.invokeAll(tasks);

		for (Future<Void> future : futures) {
			future.get();
		}

		executor.shutdown();

		log.info("End application");
	}

	private Void runQuery(int i) {
		MDC.put(MDC_FEATURE, String.format("run query %d", i));

		long count = countEntity();
		log.info("Entity count {}", count);

		MDC.remove(MDC_FEATURE);
		return null;
	}

	private long countEntity() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		CriteriaQuery<Long> query = cq.select(cb.count(cq.from(ScheduleEntity.class)));

		return getEntityManager().createQuery(query).getSingleResult();
	}
}