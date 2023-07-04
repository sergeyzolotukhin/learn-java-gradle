package ua.in.sz.logging.logs;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class MdcExecutorService {
	private final ExecutorService delegate;


	protected ExecutorService delegate() {
		return delegate;
	}


	public Future<?> submit(Runnable task) {
		return delegate.submit(new MdcRunnable(task));
	}

	public void shutdown() {
		delegate.shutdown();
	}


	public void awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
		delegate.awaitTermination(timeout, unit);
	}
}
