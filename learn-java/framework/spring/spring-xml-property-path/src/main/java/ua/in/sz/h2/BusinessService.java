package ua.in.sz.h2;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class BusinessService {
    private final List<String> names = new ArrayList<>();

    void print() {
        log.info("List of names has the following values: {}", names);
    }
}
