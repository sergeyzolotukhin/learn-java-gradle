package ua.in.sz.spring;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Slf4j
@Configuration
@EnableScheduling
@EnableAutoConfiguration
public class Application {
	public static void main(String[] args) throws InterruptedException {
		log.info("Start");
		SpringApplication.run(Application.class);
		log.info("End");
	}

	@Bean
	public JobDetail jobDetail() {
		return JobBuilder.newJob().ofType(SampleJob.class)
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger trigger(JobDetail job) {
		return TriggerBuilder.newTrigger().forJob(job)
				.withSchedule(simpleSchedule().repeatForever().withIntervalInSeconds(2))
				.build();
	}
}