package ua.in.sz.spring;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public ResponseEntity<List<String>> findByPublished() {
        return new ResponseEntity<>(List.of("Hello", "Serhij", "Zolotukhin"), HttpStatus.OK);
    }
}