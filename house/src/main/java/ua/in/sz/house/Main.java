package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.heating.ElectricityHeating;
import ua.in.sz.house.heating.Heating;
import ua.in.sz.house.heating.SolidFuelHeating;
import ua.in.sz.house.model.Block;
import ua.in.sz.house.model.House;

@Slf4j
public class Main {
    public static void main(String[] args) {
        House house = House.of(Block.CERAMICS_BRICK);
        Heating electricityHeating = ElectricityHeating.of(house);
        Heating solidFuelHeating = SolidFuelHeating.of(house);

        log.info(String.format("Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Electricity, %.0f by Solid Fuel",
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                electricityHeating.costPerYear() / 6.0,
                solidFuelHeating.costPerYear() / 6.0
        ));

        // GAS CONCRETE
        house = House.of(Block.GAS_CONCRETE_BLOCK_D300);
        electricityHeating = ElectricityHeating.of(house);
        solidFuelHeating = SolidFuelHeating.of(house);

        log.info(String.format("Heat loss is %.2f KWt on wall square %.0f M2. " +
                        "Heating cost: %.0f by Electricity, %.0f by Solid Fuel",
                house.getHeatLoss(24.0, -20.0) / 1000.0,
                house.getWallSquare(),
                electricityHeating.costPerYear() / 6.0,
                solidFuelHeating.costPerYear() / 6.0
        ));
    }
}
