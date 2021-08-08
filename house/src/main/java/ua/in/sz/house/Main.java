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

        log.info("House info:");
        log.info(String.format("\tsize: %.0f x %.0f m", house.getWidth(), house.getLength()));
        log.info(String.format("\twall width %.0f mm", house.getWallWidth() * 1000.0));
        log.info(String.format("\twall heat less %.2f kWt", house.getHeatLoss(TARGET_TEMPERATURE, -20.0) / 1000.0));

        log.info("House materials:");
        log.info(String.format("\tblock count %.0f", house.blockCount()));

        log.info("House support cost:");
        log.info(String.format("\theating cost per month %.0f UAH", costCalculator.costPerYear() / 12.0));
    }
}
