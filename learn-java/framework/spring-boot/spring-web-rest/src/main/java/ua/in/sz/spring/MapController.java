package ua.in.sz.spring;


import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/map")
public class MapController {
    @PostMapping
    public String findByPublished(@RequestBody String text) {
        log.info("A get request has received 123: [{}]", text);
        return "OK";
    }
}