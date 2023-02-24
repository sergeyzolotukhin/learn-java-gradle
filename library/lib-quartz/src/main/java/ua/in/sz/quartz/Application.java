package ua.in.sz.quartz;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Application {
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		log.info("Start");

		SchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();

		scheduler.start();

		String baseCalendarName = "my-base=calendar";
		Calendar baseCalendar = new TraceCalendar(baseCalendarName);
		scheduler.addCalendar(baseCalendarName, baseCalendar, true, true);

		String calendarName = "my-calendar";
		Calendar calendar = new TraceCalendar(calendarName, baseCalendarName);
		scheduler.addCalendar(calendarName, calendar, true, true);

		JobDetail job = JobBuilder.newJob(MyJob.class)
				.withIdentity("myJob", "group1")
				.build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(2)
						.withRepeatCount(4))
				.modifiedByCalendar(calendarName)
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
