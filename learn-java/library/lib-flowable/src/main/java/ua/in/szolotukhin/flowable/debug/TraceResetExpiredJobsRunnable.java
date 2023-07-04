package ua.in.szolotukhin.flowable.debug;

import lombok.extern.slf4j.Slf4j;
import org.flowable.common.engine.api.FlowableOptimisticLockingException;
import org.flowable.job.service.impl.asyncexecutor.AsyncExecutor;
import org.flowable.job.service.impl.asyncexecutor.FindExpiredJobsCmd;
import org.flowable.job.service.impl.asyncexecutor.ResetExpiredJobsCmd;
import org.flowable.job.service.impl.asyncexecutor.ResetExpiredJobsRunnable;
import org.flowable.job.service.impl.persistence.entity.JobInfoEntity;
import org.flowable.job.service.impl.persistence.entity.JobInfoEntityManager;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TraceResetExpiredJobsRunnable extends ResetExpiredJobsRunnable {
    public TraceResetExpiredJobsRunnable(
            String name, AsyncExecutor asyncExecutor,
            JobInfoEntityManager<? extends JobInfoEntity> jobEntityManager
    ) {
        super(name, asyncExecutor, jobEntityManager);
    }

    public void resetJobs() {
        try {
            List<? extends JobInfoEntity> expiredJobs = asyncExecutor.getJobServiceConfiguration().getCommandExecutor()
                    .execute(new FindExpiredJobsCmd(asyncExecutor.getResetExpiredJobsPageSize(), jobEntityManager));

            List<String> expiredJobIds = new ArrayList<>(expiredJobs.size());
            for (JobInfoEntity expiredJob : expiredJobs) {
                expiredJobIds.add(expiredJob.getId());
            }

            if (expiredJobIds.size() > 0) {
                log.info("Job will be reset {}", expiredJobIds);
                asyncExecutor.getJobServiceConfiguration().getCommandExecutor().execute(
                        new ResetExpiredJobsCmd(expiredJobIds, jobEntityManager));
            }

        } catch (Throwable e) {
            if (e instanceof FlowableOptimisticLockingException) {
                log.info("Optimistic lock exception while resetting locked jobs", e);
            } else {
                log.error("exception during resetting expired jobs: {}", e.getMessage(), e);
            }
        }
    }
}
