package ua.in.sz.modules.persistence.api;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.modules.persistence.api.dto.ValueDto;
import ua.in.sz.modules.persistence.impl.RepositoryImpl;

@Slf4j
public class Repository {

    public ValueDto save() {
        log.info("Repository");

        RepositoryImpl implemetation = new RepositoryImpl();
        implemetation.save();

        return new ValueDto("Repository");
    }
}
