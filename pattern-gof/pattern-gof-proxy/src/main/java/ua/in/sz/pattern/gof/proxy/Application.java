package ua.in.sz.pattern.gof.proxy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.gof.proxy.impl.RealSubject;
import ua.in.sz.pattern.gof.proxy.impl.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Subject subject = new RealSubject();

		Subject proxy = (Subject) Proxy.newProxyInstance(
				Application.class.getClassLoader(),
				new Class[]{Subject.class},
				new DynamicInvocationHandler(subject));

		String result = proxy.execute("Serhij Zolotukhin");

		log.info("Result: [{}]", result);
	}

	@RequiredArgsConstructor
	public static class DynamicInvocationHandler implements InvocationHandler {
		private final Subject subject;

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) {
			log.info("before [{}]", method.getName());

			String result = subject.execute((String) args[0]);

			log.info("after [{}]", method.getName());

			return String.format("Proxy say: %s", result);
		}
	}
}
