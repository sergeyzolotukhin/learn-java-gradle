package ua.in.sz.quartz;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Application {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		log.info("Start");

		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();

		scheduler.start();

		JobDetail job = JobBuilder.newJob(MyJob.class)
				.withIdentity("myJob", "group1")
				.build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(2)
						.withRepeatCount(4))
				.build();

		scheduler.scheduleJob(job, trigger);

		Thread.sleep(10_000);

		scheduler.shutdown(true);
		log.info("End");
	}

	@DisallowConcurrentExecution
	public static class MyJob implements Job {
		private final static AtomicInteger counter = new AtomicInteger();

		@Override
		@SneakyThrows
		public void execute(JobExecutionContext context) {
			log.info("Start: {}", counter.incrementAndGet());

			Thread.sleep(4_000);

			log.info("End: {}", counter.decrementAndGet());
		}
	}
}
