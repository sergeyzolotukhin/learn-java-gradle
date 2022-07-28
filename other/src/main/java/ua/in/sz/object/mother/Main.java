package ua.in.sz.object.mother;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        List<EntityVO> entities = new ArrayList<>();

        entities.add(
                EntityVO.builder()
                        .from("2022-01-01")
                        .to("2022-01-02")
                        .primaryRole("role-1")
                        .identification("Id-1")
                        .factors(Collections.singletonList(
                                FactorVO.builder()
                                        .from("2022-01-01")
                                        .to("2022-01-01")
                                        .type("factor-type-1")
                                        .value(10)
                                        .build()
                        ))
                        .build()
        );

        log.info("Entities: {}", entities);
    }
}
