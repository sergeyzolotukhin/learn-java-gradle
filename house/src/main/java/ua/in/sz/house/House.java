package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.model.Block;
import ua.in.sz.house.model.Wall;

@Slf4j
public class House {
    public static void main(String[] args) {
        Wall wall = Wall.builder()
                .block(Block.GAS_CONCRETE_BLOCK_D300)
                .length(10)
                .height(3)
                .build();

        double heatLoss = wall.getHeatLoss(20.0, -20.0);
        log.info("Heat loss is {} KWt on wall square {} M2", heatLoss / 1000.0,
                wall.getSquare());
    }
}
