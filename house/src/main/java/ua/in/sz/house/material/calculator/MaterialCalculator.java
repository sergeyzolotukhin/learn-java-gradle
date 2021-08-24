package ua.in.sz.house.material.calculator;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.building.House;
import ua.in.sz.house.material.BillOfMaterials;
import ua.in.sz.house.material.Material;

import java.util.Arrays;

@Slf4j
public class MaterialCalculator {

    public static BillOfMaterials<Material> calculate(House house) {
        Material cementMortar = CementMortarCalculator.calculate(house);
        Material brick = BrickCalculator.calculate(house);

        Material cement = CementCalculator.calculate(cementMortar);
        Material sang = SangCalculator.calculate(cementMortar);

        return BillOfMaterials.of(Arrays.asList(brick, cement, sang));
    }
}
