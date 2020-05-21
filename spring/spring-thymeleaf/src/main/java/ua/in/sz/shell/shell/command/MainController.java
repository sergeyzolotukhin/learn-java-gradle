package ua.in.sz.shell.shell.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class MainController {
    @GetMapping("/index")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {
        log.info("Get faster");
        return "index";
    }
}