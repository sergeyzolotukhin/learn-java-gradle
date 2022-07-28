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

        entities.add(
                entity("id-2")
                        .factor("factor-type-1", 1)
                        .factor("factor-type-2", 12)
                        .factor("factor-type-2", 13)
                        .build());
        entities.add(
                entity("id-3").primaryRole("Role-2")
                        .factor("factor-type-1", 1)
                        .build());
        entities.add(
                entity("id-3")
                        .factor(commission(true))
                        .build());
        entities.add(
                entity("id-4")
                        .factor(commission(false))
                        .build());


        entities.forEach(e -> log.info("Entity: {}", e));
    }

    private static EntityVO.EntityVOBuilder entity(String identification) {
        return EntityVO.builder()
                .from("2022-01-01")
                .to("2022-01-02")
                .primaryRole("role-1")
                .identification(identification);
    }

    private static FactorVO commission(boolean value) {
        return FactorVO.builder()
                .type("is_commission")
                .value(value)
                .build();
    }
}
