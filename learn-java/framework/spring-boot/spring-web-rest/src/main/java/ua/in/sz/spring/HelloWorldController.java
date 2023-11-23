package ua.in.sz.spring;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public List<String> findByPublished() {
        log.info("A get request has received");
        return List.of("Hello", "Serhij", "Zolotukhin");
    }
}