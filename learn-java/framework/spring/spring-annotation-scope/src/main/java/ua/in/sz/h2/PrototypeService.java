package ua.in.sz.h2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE
        , proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class PrototypeService {
    public void print() {
        log.info("Hello {}", this);
    }
}
