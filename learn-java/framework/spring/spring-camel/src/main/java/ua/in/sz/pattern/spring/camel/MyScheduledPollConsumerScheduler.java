package ua.in.sz.pattern.spring.camel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.Consumer;
import org.apache.camel.spi.ScheduledPollConsumerScheduler;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component("hbmquartz")
public class MyScheduledPollConsumerScheduler implements ScheduledPollConsumerScheduler {
	private String cron;

	@Override
	public void onInit(Consumer consumer) {
		log.info("init !!!!!");
	}

	@Override
	public void scheduleTask(Runnable task) {

	}

	@Override
	public void unscheduleTask() {

	}

	@Override
	public void startScheduler() {

	}

	@Override
	public boolean isSchedulerStarted() {
		return false;
	}

	@Override
	public void setCamelContext(CamelContext camelContext) {

	}

	@Override
	public CamelContext getCamelContext() {
		return null;
	}

	@Override
	public void shutdown() throws Exception {

	}

	@Override
	public void start() throws Exception {

	}

	@Override
	public void stop() throws Exception {

	}
}
