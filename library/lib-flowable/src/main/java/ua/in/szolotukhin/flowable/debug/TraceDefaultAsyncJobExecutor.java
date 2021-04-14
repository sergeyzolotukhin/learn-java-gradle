package ua.in.szolotukhin.flowable.debug;

import org.flowable.job.service.impl.asyncexecutor.AcquireAsyncJobsDueRunnable;
import org.flowable.job.service.impl.asyncexecutor.AcquireTimerJobsRunnable;
import org.flowable.job.service.impl.asyncexecutor.DefaultAsyncJobExecutor;
import org.flowable.job.service.impl.asyncexecutor.ResetExpiredJobsRunnable;
import org.flowable.job.service.impl.persistence.entity.JobInfoEntity;
import org.flowable.job.service.impl.persistence.entity.JobInfoEntityManager;
import org.flowable.job.service.impl.util.CommandContextUtil;

public class TraceDefaultAsyncJobExecutor extends DefaultAsyncJobExecutor {
    protected void initializeRunnables() {
        if (timerRunnableNeeded && timerJobRunnable == null) {
            timerJobRunnable = new AcquireTimerJobsRunnable(this, jobServiceConfiguration.getJobManager());
        }

        JobInfoEntityManager<? extends JobInfoEntity> jobEntityManagerToUse = jobEntityManager != null
                ? jobEntityManager : CommandContextUtil.getJobServiceConfiguration().getJobEntityManager();

        if (resetExpiredJobsRunnable == null) {
            String resetRunnableName = resetExpiredRunnableName != null ? resetExpiredRunnableName : "flowable-reset-expired-jobs";
            resetExpiredJobsRunnable = new TraceResetExpiredJobsRunnable(resetRunnableName, this, jobEntityManagerToUse);
        }

        if (!isMessageQueueMode && asyncJobsDueRunnable == null) {
            String acquireJobsRunnableName = acquireRunnableThreadName != null ? acquireRunnableThreadName : "flowable-acquire-async-jobs";
            asyncJobsDueRunnable = new AcquireAsyncJobsDueRunnable(acquireJobsRunnableName, this, jobEntityManagerToUse);
        }
    }
}
