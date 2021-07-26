package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.Block;
import ua.in.sz.house.model.House;

@Slf4j
public class Main {
    public static void main(String[] args) {
        House ceramicsHouse = House.of(Block.CERAMICS_BRICK);
        double costPerYear = ElectricityHeating.costPerYear(ceramicsHouse);

        log.info("Heat loss is {} KWt on wall square {} M2",
                String.format("%.2f",ceramicsHouse.getHeatLoss(24.0, -20.0) / 1000.0),
                ceramicsHouse.getWallSquare());
        log.info("Electricity cost per month is {} UAH", String.format("%.2f",costPerYear / 6.0));

        // GAS CONCRETE
        House gasConcreteHouse = House.of(Block.GAS_CONCRETE_BLOCK_D300);

        log.info("Heat loss is {} KWt on wall square {} M2",
                String.format("%.2f",gasConcreteHouse.getHeatLoss(24.0, -20.0) / 1000.0),
                gasConcreteHouse.getWallSquare());
        costPerYear = ElectricityHeating.costPerYear(gasConcreteHouse);
        log.info("Electricity cost per month is {} UAH", String.format("%.2f",costPerYear / 6.0));
    }
}
