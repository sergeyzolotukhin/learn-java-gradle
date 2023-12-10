package ua.in.sz.modules.business.api;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.modules.persistence.api.Repository;
import ua.in.sz.modules.persistence.api.dto.ValueDto;
//import ua.in.sz.modules.persistence.impl.RepositoryImpl;

@Slf4j
public class Service {

    public String print() {
        Repository repository = new Repository();

//        RepositoryImpl implementation = new RepositoryImpl();
//        implementation.save();

        log.info("Service");
        ValueDto save = repository.save();
        return "Service, " + save;
    }
}
