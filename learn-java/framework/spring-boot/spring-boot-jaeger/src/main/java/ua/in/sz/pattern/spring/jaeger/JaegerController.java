package ua.in.sz.pattern.spring.jaeger;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping
public class JaegerController {

    private final Random random = new Random();

    private final RestTemplate restTemplate;
    @Value("${spring.application.name}")
    private String applicationName;

    public JaegerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/path1")
    public ResponseEntity<String> path1() {
        log.info("Incoming request at {} for request /path1 ", applicationName);
        doSomething();
        String response = restTemplate.getForObject("http://localhost:8080/path2", String.class);
        doSomething();
        return ResponseEntity.ok("response from /path1 + " + response);
    }

    @GetMapping("/path2")
    public ResponseEntity<String>  path2() {
        log.info("Incoming request at {} at /path2", applicationName);
        doSomething();
        return ResponseEntity.ok("response from /path2 ");
    }

    @SneakyThrows
    public void doSomething() {
        int sleepTime = random.nextInt(2, 5);
        log.info("sleeping for {} seconds", sleepTime);
        TimeUnit.SECONDS.sleep(sleepTime);
    }

}
