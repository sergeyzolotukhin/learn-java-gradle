package ua.in.sz.house.cost;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.material.MaterialCalculator;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class MaterialCostCalculator {
    private final MaterialCalculator materialCalculator;

    public double blockCost() {
        return materialCalculator.blockCount() * 3.3;
    }

    public double cementCost() {
        return materialCalculator.cementKg() / 1000.0 * 1800.0;
    }

    public double sangCost() {
        return materialCalculator.sangKg() / 1000.0 * 180.0;
    }
}
