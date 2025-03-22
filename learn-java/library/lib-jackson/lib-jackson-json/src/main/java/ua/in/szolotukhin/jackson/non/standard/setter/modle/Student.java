package ua.in.szolotukhin.jackson.non.standard.setter.modle;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Student {
    public int rollNo;
    public String name;

    @JsonSetter("name")
    public void setTheName(String name) {
        log.info("call setTheName: [{}]", name);
        this.name = name;
    }
}
