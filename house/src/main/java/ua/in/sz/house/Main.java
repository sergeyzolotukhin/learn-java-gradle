package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.Boiler;
import ua.in.sz.house.boiler.ElectricityBoiler;
import ua.in.sz.house.boiler.ResourceCostCalculator;
import ua.in.sz.house.heating.GasHeating;
import ua.in.sz.house.heating.Heating;
import ua.in.sz.house.heating.SolidFuelHeating;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        TempCalendar calendar = TempCalendar.of();
        Boiler boiler = ElectricityBoiler.of();
        House house = House.of(Block.CERAMICS_BRICK, boiler);

        ResourceCostCalculator costCalculator = ResourceCostCalculator.of(house, calendar);
        log.info(String.format("Wall width %.0f. Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Electricity",
                house.getWallWidth() * 1000,
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                costCalculator.costPerYear() / 12.0
        ));

        Heating heating = SolidFuelHeating.of(house, calendar);
        log.info(String.format("Wall width %.0f. Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Solid Fuel",
                house.getWallWidth() * 1000,
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                heating.costPerYear() / 12.0
        ));

        heating = GasHeating.of(house, calendar);
        log.info(String.format("Wall width %.0f. Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Gas",
                house.getWallWidth() * 1000,
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                heating.costPerYear() / 6.0
        ));


        // GAS CONCRETE
        house = House.of(Block.GAS_CONCRETE_BLOCK_D500, boiler);
        costCalculator = ResourceCostCalculator.of(house, calendar);
        Heating solidFuelHeating = SolidFuelHeating.of(house, calendar);
        Heating gasHeating = GasHeating.of(house, calendar);

        log.info(String.format("Wall width %.0f. Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Electricity, %.0f by Solid Fuel, %.0f by Gas",
                house.getWallWidth() * 1000,
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                costCalculator.costPerYear() / 12.0,
                solidFuelHeating.costPerYear() / 12.0,
                gasHeating.costPerYear() / 6.0
        ));
    }
}
