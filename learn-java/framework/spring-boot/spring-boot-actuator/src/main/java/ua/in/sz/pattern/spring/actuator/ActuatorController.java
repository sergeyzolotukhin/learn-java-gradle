package ua.in.sz.pattern.spring.actuator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActuatorController {

	@GetMapping("/hello-world")
	@ResponseBody
	public Greeting sayHello() {
		return new Greeting("Hello, Serhij!");
	}

}
