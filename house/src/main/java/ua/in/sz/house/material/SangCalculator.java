package ua.in.sz.house.material;

import lombok.AllArgsConstructor;
import ua.in.sz.house.building.House;

@AllArgsConstructor(staticName = "of")
public class SangCalculator {
    private final House house;

    public double sangKg() {
        CementMortarCalculator cementMortarCalculator = CementMortarCalculator.of(house);

        double cementMortar = cementMortarCalculator.cementMortar();
        double volume = cementMortar * 3.0 / 4.0;
        return volume * 1500; // кг в м3 цемента
    }
}
