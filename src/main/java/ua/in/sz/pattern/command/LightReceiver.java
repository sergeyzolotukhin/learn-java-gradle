package ua.in.sz.pattern.command;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class LightReceiver {
    void turnOn(){
        log.info("The light is on");
    }

    void turnOff(){
        log.info("The light is off");
    }
}
