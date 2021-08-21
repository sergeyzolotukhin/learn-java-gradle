package ua.in.sz.house.material;


import ua.in.sz.house.building.House;

public class CementCalculator implements MaterialCalculator {
    private final House house;

    public CementCalculator(House house) {
        this.house = house;
    }

    public static CementCalculator of(House house) {
        return new CementCalculator(house);
    }

    @Override
    public double calculate() {
        CementMortarCalculator cementMortarCalculator = CementMortarCalculator.of(house);

        double cementMortar = cementMortarCalculator.calculate();
        double volume = cementMortar / 4.0;
        return volume * 1625; // кг в м3 песка
    }
}
