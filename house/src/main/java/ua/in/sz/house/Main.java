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

        house = House.of(Block.CERAMICS_BRICK, SolidFuelBoiler.of());
        costCalculator = ResourceCostCalculator.of(house, calendar);
        log.info(String.format("Wall width %.0f. Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Solid Fuel",
                house.getWallWidth() * 1000,
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                costCalculator.costPerYear() / 12.0
        ));

        house = House.of(Block.CERAMICS_BRICK, GasBoiler.of());
        costCalculator = ResourceCostCalculator.of(house, calendar);
        log.info(String.format("Wall width %.0f. Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Gas",
                house.getWallWidth() * 1000,
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                costCalculator.costPerYear() / 6.0
        ));


        // GAS CONCRETE
        house = House.of(Block.GAS_CONCRETE_BLOCK_D500, boiler);
        costCalculator = ResourceCostCalculator.of(house, calendar);
//        Heating solidFuelHeating = SolidFuelHeating.of(house, calendar);
//        Heating gasHeating = GasHeating.of(house, calendar);

        log.info(String.format("Wall width %.0f. Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Electricity, ? by Solid Fuel,  by Gas",
                house.getWallWidth() * 1000,
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                costCalculator.costPerYear() / 12.0
//                solidFuelHeating.costPerYear() / 12.0,
//                gasHeating.costPerYear() / 6.0
        ));
    }
}
