package ua.in.sz.pattern.spring.actuator;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActuatorController {

	@GetMapping("/hello-world")
	@ResponseBody
    @Timed(value = "request.greeting", extraTags = {"env", "local"})
	public Greeting sayHello() {
		return new Greeting("Hello, Serhij!");
	}

}
