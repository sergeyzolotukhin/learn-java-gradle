package ua.in.sz.house.material;

import com.google.common.collect.ImmutableList;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.building.House;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class MaterialCalculators {
    private final House house;

    public static List<Class<? extends MaterialCalculator>> calculators =
            ImmutableList.<Class<? extends MaterialCalculator>>builder()
                    .add(BrickCalculator.class)
                    .add(CementCalculator.class)
                    .add(CementMortarCalculator.class)
                    .add(SangCalculator.class)
                    .build();

    public List<Material> calculate() {
        return calculators.stream()
                .map(this::createCalculator)
                .map(MaterialCalculator::calculate)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private MaterialCalculator createCalculator(Class<? extends MaterialCalculator> clazz) {
        return clazz.getDeclaredConstructor(House.class).newInstance(house);
    }
}
