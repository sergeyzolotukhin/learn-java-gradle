package ua.in.szolotukhin.flowable.common;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.job.service.impl.asyncexecutor.ResetExpiredJobsRunnable;
import ua.in.szolotukhin.flowable.debug.TraceDefaultAsyncJobExecutor;
import ua.in.szolotukhin.flowable.debug.TraceResetExpiredJobsRunnable;
import ua.in.szolotukhin.flowable.debug.TraceStandaloneProcessEngineConfiguration;

@Slf4j
public class ProcessEngineFactory {
	private static final boolean HOME = true;

	public static ProcessEngine createEngine(boolean recreateSchema) {

		log.info("Create engine");

		StandaloneProcessEngineConfiguration cfg = new TraceStandaloneProcessEngineConfiguration();

        if (HOME) {
//            cfg.setJdbcUrl("jdbc:h2:mem:");
            cfg.setJdbcUrl("jdbc:postgresql://localhost/flowable")
                    .setJdbcUsername("flowable")
                    .setJdbcPassword("flowable")
                    .setJdbcDriver("org.postgresql.Driver");
        } else {
            cfg.setJdbcUrl("jdbc:oracle:thin:@SZolotukhin-ge1.gedc.luxoft.com:1521/XE")
                    .setJdbcUsername("FLOWABLE_OWNER")
                    .setJdbcPassword("FLOWABLE_OWNER")
                    .setJdbcDriver("oracle.jdbc.driver.OracleDriver")
                    .setDatabaseSchema("FLOWABLE_OWNER");
        }

        if (recreateSchema) {
            cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_DROP_CREATE);
        } else {
            cfg.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        }

		cfg.setAsyncExecutorActivate(true);
        int timeout = 1000;
//        cfg.setAsyncExecutorResetExpiredJobsMaxTimeout(timeout);
        cfg.setAsyncExecutorAsyncJobLockTimeInMillis(timeout);
        cfg.setAsyncExecutorResetExpiredJobsInterval(1000);
//        cfg.setAsyncExecutorTimerLockTimeInMillis(timeout);
//		cfg.setEnableVerboseExecutionTreeLogging(true);

        return cfg.buildProcessEngine();
    }
}
