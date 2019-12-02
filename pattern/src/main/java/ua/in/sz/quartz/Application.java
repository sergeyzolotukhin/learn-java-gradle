package ua.in.sz.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

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

	public static class MyJob implements Job {
		@Override
		public void execute(JobExecutionContext context) {
			log.info("Execute");
		}
	}
}
