package ua.in.sz.logging.logs;

import com.google.common.util.concurrent.ForwardingExecutorService;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RequiredArgsConstructor
public class MdcExecutorService extends ForwardingExecutorService {
	private final ExecutorService delegate;

	@Override
	protected ExecutorService delegate() {
		return delegate;
	}

	@Override
	public Future<?> submit(Runnable task) {
		return super.submit(new MdcRunnable(task));
	}
}
