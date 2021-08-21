package ua.in.sz.house.material;

import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.building.House;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(staticName = "of")
public class AllMaterialCalculator {
    private final House house;

    public List<Class<? extends MaterialCalculator>> calculatorClasses;

    {
        calculatorClasses = ImmutableList.<Class<? extends MaterialCalculator>>builder()
                .add(BrickCalculator.class)
                .add(CementCalculator.class)
                .add(CementMortarCalculator.class)
                .add(SangCalculator.class)
                .build();
    }

    public List<Double> calculate() {
        return calculatorClasses.stream()
                .map(this::createCalculator)
                .map(MaterialCalculator::calculate)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private MaterialCalculator createCalculator(Class<? extends MaterialCalculator> clazz) {
        return clazz.getDeclaredConstructor(House.class).newInstance(house);
    }

    public double blockCount(){
        return BrickCalculator.of(house).calculate();
    }

    public double cementMortar() {
        return CementMortarCalculator.of(house).calculate();
    }

    public double cementKg() {
        return CementCalculator.of(house).cementKg();
    }

    public double sangKg() {
        return SangCalculator.of(house).sangKg();
    }
}
