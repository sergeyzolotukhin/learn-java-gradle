package ua.in.sz.spring;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/external")
public class ExternalController {
    @GetMapping("/hello")
    public List<String> findByPublished(HttpSession session) {
        log.info("A get request has received: [{}]", session.getId());
        return List.of("Hello external", "Serhij", "Zolotukhin");
    }
}