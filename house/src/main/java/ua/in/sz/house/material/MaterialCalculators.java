package ua.in.sz.house.material;

import com.google.common.collect.ImmutableList;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.building.House;
import ua.in.sz.house.material.impl.BrickCalculator;
import ua.in.sz.house.material.impl.CementCalculator;
import ua.in.sz.house.material.impl.CementMortarCalculator;
import ua.in.sz.house.material.impl.SangCalculator;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MaterialCalculators {
    public static List<Class<? extends MaterialCalculator>> calculators =
            ImmutableList.<Class<? extends MaterialCalculator>>builder()
                    .add(BrickCalculator.class)
                    .add(CementCalculator.class)
                    .add(CementMortarCalculator.class)
                    .add(SangCalculator.class)
                    .build();

    public static List<Material> calculate(House house) {
        return calculators.stream()
                .map(c -> createCalculator(c, house))
                .map(MaterialCalculator::calculate)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private static MaterialCalculator createCalculator(Class<? extends MaterialCalculator> clazz, House house) {
        return clazz.getDeclaredConstructor(House.class).newInstance(house);
    }
}
