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
        log.info(String.format("Wall width %.0f. Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Electricity",
                house.getWallWidth() * 1000,
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                costCalculator.costPerYear() / 12.0
        ));
    }
}
