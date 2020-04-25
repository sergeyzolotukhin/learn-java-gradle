package ua.in.sz.togglz.togglz;

import lombok.extern.slf4j.Slf4j;
import org.togglz.core.logging.Log;
import org.togglz.core.spi.LogProvider;


@Slf4j
public class Slf4JLogProvider implements LogProvider {
	@Override
	public Log getLog(String name) {
		return new TogglzSlf4JLog(name);
	}

	@Override
	public int priority() {
		return 0;
	}
}
