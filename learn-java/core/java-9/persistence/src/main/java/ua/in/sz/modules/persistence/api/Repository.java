package ua.in.sz.modules.persistence.api;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Repository {

    public String save() {
        log.info("Repository");
        return "Repository";
    }
}
