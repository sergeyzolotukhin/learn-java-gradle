package ua.in.sz.house.material;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.building.House;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class AllMaterialCalculator {
    private final House house;

    public double blockCount(){
        return BrickCalculator.of(house).blockCount();
    }

    public double cementMortar() {
        return CementMortarCalculator.of(house).cementMortar();
    }

    public double cementKg() {
        return CementCalculator.of(house).cementKg();
    }

    public double sangKg() {
        return SangCalculator.of(house).sangKg();
    }
}
