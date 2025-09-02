package ua.in.sz.pattern.spring.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@EnableBatchProcessing
@SpringBootApplication
public class BatchApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BatchApplication.class, args);

        final JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        final Job job = (Job) context.getBean("taskletJob");

        log.info("Starting the batch job");
        try {
            final JobExecution execution = jobLauncher.run(job, new JobParameters());
            log.info("Job Status : {}", execution.getStatus());
        } catch (final Exception e) {
            log.error("Job failed", e);
        }
    }
}
