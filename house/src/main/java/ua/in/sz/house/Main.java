package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.Boiler;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.boiler.impl.GasBoiler;
import ua.in.sz.house.boiler.impl.SolidFuelBoiler;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        House house = House.builder()
                .block(Block.CERAMICS_BRICK)
                .boiler(new ElectricityBoiler())
                .size(10.0, 10.0, 3.0)
                .build();

        ResourceCostCalculator costCalculator = ResourceCostCalculator.of(house);

        String houseInfo = "\nHouse info:" +
                String.format("\n\tsize: %.0f x %.0f m", house.getWidth(), house.getLength()) +
                String.format("\n\twall width %.0f mm", house.getWallWidth() * 1000.0) +
                String.format("\n\twall heat less %.2f kWt", house.getHeatLoss(TARGET_TEMPERATURE, -20.0) / 1000.0);

        String materialInfo = "\nHouse materials:" +
                String.format("\n\tblock count %.0f cost %.0f UAH", house.blockCount(), house.blockCount() * 3.3) +
                String.format("\n\tcement mortar %.2f M3", house.cementMortar());

        String supportCostInfo = "\nHouse support cost:" +
                String.format("\n\theating cost per month %.0f UAH", costCalculator.costPerYear() / 12.0);

        log.info(houseInfo + materialInfo + supportCostInfo);
    }
}
