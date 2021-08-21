package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.report.HouseReport;
import ua.in.sz.house.report.MaterialReport;
import ua.in.sz.house.report.SupportReport;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        House house = House.builder()
                .block(Block.CERAMICS_BRICK)
                .boiler(new ElectricityBoiler())
                .size(10.0, 10.0, 3.0)
                .build();

        String houseInfo = HouseReport.of(house).report();
        String materialInfo = MaterialReport.of(house).report();
        String supportCostInfo = SupportReport.of(house).report();

        log.info(houseInfo + materialInfo + supportCostInfo);
    }
}
