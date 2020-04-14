package ua.in.sz.exception.handling;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class Application {
	public static void main(String[] args) throws ExecutionException {
		try {
			method_1();
		} catch (UnsupportedOperationException e) {
			log.error("Exception handling", e);
		}

	}

	// rethrow exception (save stack trace unchanged)

	private static void method_1() {
		try {
			method_2();
		} catch (UnsupportedOperationException e) {
			log.info("Exception in method");
			throw e;
		}

	}

	private static void method_2() {
		throw new UnsupportedOperationException();
	}

}
