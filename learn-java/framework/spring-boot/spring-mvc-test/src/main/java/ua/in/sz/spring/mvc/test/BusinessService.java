package ua.in.sz.spring.mvc.test;

import org.springframework.stereotype.Service;


@Service
public class BusinessService {
    public String getName() {
        return this.getClass().getName();
    }
}
