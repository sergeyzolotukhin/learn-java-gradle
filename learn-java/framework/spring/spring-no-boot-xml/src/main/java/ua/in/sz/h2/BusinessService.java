package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Getter
//@Setter
public class BusinessService {
//    private String myName;
//    private String mySecondName;
    private List<String> list;

    public void setList(String ... list) {
        this.list = Arrays.asList(list);
    }

    void print() {
//        log.info("Hello [{}] ! Second name [{}] !", myName, mySecondName);
        log.info("List: {}", list);
    }
}
