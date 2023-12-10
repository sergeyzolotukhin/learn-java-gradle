package ua.in.sz.modules.business;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.modules.persistence.Repository;

@Slf4j
public class Service {

    public String print() {
        Repository repository = new Repository();

        log.info("Service");
        return "Service, " + repository.save();
    }
}
