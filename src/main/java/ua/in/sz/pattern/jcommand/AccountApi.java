package ua.in.sz.pattern.jcommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Service
public class AccountApi {

    @PostConstruct
    public void init() {
        log.info("Account API");
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroy API");
    }

    /*
    private final CommandGateway commandGateway;

    private final EventStore eventStore;

    public AccountApi(CommandGateway commandGateway, EventStore eventStore) {
        this.commandGateway = commandGateway;
        this.eventStore = eventStore;
    }
    */
}
