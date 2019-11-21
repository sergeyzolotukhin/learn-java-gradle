package ua.in.sz.pattern.aspect.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public void execute() {
        log.info("Execute");
    }
}
