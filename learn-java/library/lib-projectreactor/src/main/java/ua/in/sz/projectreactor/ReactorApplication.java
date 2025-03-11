package ua.in.sz.projectreactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ReactorApplication {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("data1", "data2", "data3");
        flux.subscribe(log::info);
    }
}
