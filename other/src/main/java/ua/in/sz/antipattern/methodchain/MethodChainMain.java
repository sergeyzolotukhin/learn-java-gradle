package ua.in.sz.antipattern.methodchain;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Null object pattern
 * Law of Demeter
 * 		Each unit should have only limited knowledge about other units: only units "closely" related to the current unit.
 * 		Each unit should only talk to its friends; don't talk to strangers.
 * 		Only talk to your immediate friends.
 */
@Slf4j
public class MethodChainMain {
	public static void main(String[] args) {
		A first = new A();
//		A first = null;
		String text = Optional.ofNullable(first)
				.map(I::next)
				.map(I::next)
				.map(I::next)
				.map(I::text)
				.orElse(null);

		text = opt(() -> first.next().next().next().text());
		log.info("Text: {}", text);

		text = requireNonNull(requireNonNull(requireNonNull(requireNonNull(
				first).next()).next()).next()).text();

		log.info("Txt: {}", text);
	}

	private static <T> T opt(Supplier<T> statement) {
		try {
			return statement.get();
		} catch (NullPointerException exc) {
			log.warn("npe");
			return null;
		}
	}

	public interface I {
		default I next() {
			return null;
		}

		default String text() {
			throw new UnsupportedOperationException();
		}
	}

	static class A implements I {
		public I next() {
			return new B();
//			return null;
		}
	}

	static class B implements I {
		public C next() {
			return new C();
//			return null;
		}
	}

	static class C implements I {
		public D next() {
			return new D();
		}
	}

	static class D implements I {
		public String text() {
			return "Text";
		}
	}
}
