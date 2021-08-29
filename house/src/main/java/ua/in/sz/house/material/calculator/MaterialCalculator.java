package ua.in.sz.house.material.calculator;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.house.House;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.MaterialUnit;

@Slf4j
public class MaterialCalculator {

    public static Material calculate(House house) {
        Material building = new Material(MaterialType.HOUSE, 1, MaterialUnit.PIECE, 0.0);

        Material wall = new Material(MaterialType.WALL, 1, MaterialUnit.PIECE, 0.0);
        wall.add(BrickCalculator.calculate(house));
        wall.add(cementMortar(house));

        building.add(wall);
        building.add(FoundationCalculator.calculate(house));

        return building;
    }

    private static Material cementMortar(House house) {
        Material cementMortar = CementMortarCalculator.calculate(house);
        cementMortar.add(CementCalculator.calculate(cementMortar));
        cementMortar.add(SangCalculator.calculate(cementMortar));
        return cementMortar;
    }
}
