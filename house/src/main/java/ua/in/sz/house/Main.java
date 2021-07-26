package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.Block;
import ua.in.sz.house.model.House;

@Slf4j
public class Main {
    public static void main(String[] args) {
        House ceramicsHouse = House.of(Block.CERAMICS_BRICK);
        ElectricityHeating electricityHeating = ElectricityHeating.of(ceramicsHouse);

        log.info(String.format("Heat loss is %.2f KWt on wall square %.0f M2. Heating cost %.0f by Electricity",
                ceramicsHouse.getHeatLoss(24.0, -20.0) / 1000.0,
                ceramicsHouse.getWallSquare(),
                electricityHeating.costPerYear() / 6.0
        ));

        // GAS CONCRETE
        House gasConcreteHouse = House.of(Block.GAS_CONCRETE_BLOCK_D300);
        electricityHeating = ElectricityHeating.of(gasConcreteHouse);

        log.info(String.format("Heat loss is %.2f KWt on wall square %.0f M2. Heating cost %.0f by Electricity",
                gasConcreteHouse.getHeatLoss(24.0, -20.0) / 1000.0,
                gasConcreteHouse.getWallSquare(),
                electricityHeating.costPerYear() / 6.0
        ));
    }
}
