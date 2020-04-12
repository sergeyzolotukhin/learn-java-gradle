package ua.in.sz.pattern.gof.proxy;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.pattern.gof.proxy.impl.RealSubject;
import ua.in.sz.pattern.gof.proxy.impl.Subject;

@Slf4j
public class Application {
	public static void main(String[] args) {
		Subject subject = new RealSubject();

		String result = subject.execute("Serhij Zolotukhin");

		log.info("Result: [{}]", result);
	}
}
