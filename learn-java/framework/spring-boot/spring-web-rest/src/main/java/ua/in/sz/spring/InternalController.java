package ua.in.sz.spring;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/internal")
public class InternalController {
    @GetMapping("/hello")
    public List<String> findByPublished(HttpSession session) {
        log.info("A get request has received 123: [{}]", session.getId());
        return List.of("Hello internal", "Serhij", "Zolotukhin");
    }
}