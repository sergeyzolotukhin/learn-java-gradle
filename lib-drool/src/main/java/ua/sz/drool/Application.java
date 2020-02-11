package ua.sz.drool;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


@Slf4j
public class Application {
	public static void main(String[] args) {
		log.info("start drool");

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		KieSession kSession = kContainer.newKieSession("ksession-rules");

		kSession.insert(
				Message.builder()
						.message("HW")
						.status(Message.HELLO)
						.build());

		kSession.fireAllRules();

		log.info("end drool");
	}
}
