package ua.in.sz.pattern.spring.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class JobConfiguration {

    @Bean
    public Job taskletJob(JobRepository jobRepository, Step deleteFilesInDir) {
        return new JobBuilder("taskletJob", jobRepository)
                .start(deleteFilesInDir)
                .build();
    }

    @Bean
    public Step deleteFilesInDir(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("deleteFilesInDir", jobRepository)
                .tasklet(fileDeletingTasklet(), transactionManager)
                .build();
    }

    @Bean
    public Tasklet fileDeletingTasklet() {
        return (contribution, chunkContext) -> {
            log.info("Tasklet was run");
            return null;
        };
    }
}
