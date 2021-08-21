package ua.in.sz.house.cost;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.material.AllMaterialCalculator;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class MaterialCostCalculator {
    private final AllMaterialCalculator allMaterialCalculator;

    public double blockCost() {
        return allMaterialCalculator.blockCount() * 3.3;
    }

    public double cementCost() {
        return allMaterialCalculator.cementKg() / 1000.0 * 1800.0;
    }

    public double sangCost() {
        return allMaterialCalculator.sangKg() / 1000.0 * 180.0;
    }
}
