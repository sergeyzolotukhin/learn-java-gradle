package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.Block;
import ua.in.sz.house.model.House;

@Slf4j
public class Main {
    public static void main(String[] args) {
        House ceramicsHouse = House.of(Block.CERAMICS_BRICK);

        log.info("Heat loss is {} KWt on wall square {} M2",
                formatHeatLossKW(ceramicsHouse.getHeatLoss(24.0, -20.0)),
                ceramicsHouse.getWallSquare());

        House gasConcreteHouse = House.of(Block.GAS_CONCRETE_BLOCK_D300);

        log.info("Heat loss is {} KWt on wall square {} M2",
                formatHeatLossKW(gasConcreteHouse.getHeatLoss(24.0, -20.0)),
                gasConcreteHouse.getWallSquare());
    }

    private static String formatHeatLossKW(double heatLoss) {
        return String.format("%.2f", heatLoss / 1000.0);
    }
}
