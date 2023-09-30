package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@Component
public class BusinessService {
    @Value("${my_name}")
    private String myName;
    @Value("${my_second_name}")
    private String mySecondName;

    void print() {
        log.info("Hello [{}] ! Second name [{}] !", myName, mySecondName);
    }
}
