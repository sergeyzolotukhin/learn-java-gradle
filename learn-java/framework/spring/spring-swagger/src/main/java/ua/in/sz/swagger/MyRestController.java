package ua.in.sz.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyRestController {
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
		return String.format("Hello %s!", name);
	}
}
