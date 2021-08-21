package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.material.AllMaterialCalculator;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.report.MaterialReport;

import java.util.List;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        House house = House.builder()
                .block(Block.CERAMICS_BRICK)
                .boiler(new ElectricityBoiler())
                .size(10.0, 10.0, 3.0)
                .build();

        List<Material> materials = AllMaterialCalculator.of(house).calculate();

//        log.info(HouseReport.of(house).report());
        log.info(MaterialReport.of(materials).report());
//        log.info(SupportReport.of(house).report());
    }
}
