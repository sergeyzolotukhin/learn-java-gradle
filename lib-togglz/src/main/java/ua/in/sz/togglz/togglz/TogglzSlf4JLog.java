package ua.in.sz.togglz.togglz;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.togglz.core.logging.Log;

@Slf4j
public class TogglzSlf4JLog implements Log {

	private final Logger logger;

	public TogglzSlf4JLog(String name) {
		logger = LoggerFactory.getLogger(name);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public void debug(String msg) {
		logger.debug(msg);
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
	}

	@Override
	public void warn(String msg) {
		logger.warn(msg);
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
	}

	@Override
	public void error(String msg, Throwable e) {
		logger.error(msg, e);
	}
}
