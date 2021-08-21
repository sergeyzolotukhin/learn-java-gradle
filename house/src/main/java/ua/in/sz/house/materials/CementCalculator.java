package ua.in.sz.house.materials;


import lombok.AllArgsConstructor;
import ua.in.sz.house.building.House;

@AllArgsConstructor(staticName = "of")
public class CementCalculator {
    private final House house;

    public double cementKg() {
        CementMortarCalculator cementMortarCalculator = CementMortarCalculator.of(house);

        double cementMortar = cementMortarCalculator.cementMortar();
        double volume = cementMortar / 4.0;
        return volume * 1625; // кг в м3 песка
    }
}
