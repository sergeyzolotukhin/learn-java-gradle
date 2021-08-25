package ua.in.sz.house.material.calculator;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.house.House;
import ua.in.sz.house.material.BillOfMaterial;
import ua.in.sz.house.material.Material;

@Slf4j
public class MaterialCalculator {

    public static BillOfMaterial calculate(House house) {
        Material cementMortar = CementMortarCalculator.calculate(house);
        Material cement = CementCalculator.calculate(cementMortar);
        Material sang = SangCalculator.calculate(cementMortar);

        Material brick = BrickCalculator.calculate(house);

        BillOfMaterial bom = new BillOfMaterial(null);

        BillOfMaterial cementMortarBom = new BillOfMaterial(cementMortar);
        cementMortarBom.add(cement);
        cementMortarBom.add(sang);

        bom.add(cementMortarBom);
        bom.add(brick);

        return bom;
    }
}
