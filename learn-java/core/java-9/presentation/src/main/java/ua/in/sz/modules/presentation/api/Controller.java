package ua.in.sz.modules.presentation.api;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.modules.business.api.Service;

@Slf4j
public class Controller {

    public static void main(String[] args) {
        Service service = new Service();
        log.info("Controller, {}", service.print());
    }
}
