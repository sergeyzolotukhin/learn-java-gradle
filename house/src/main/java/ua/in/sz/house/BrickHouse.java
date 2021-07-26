package ua.in.sz.house;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class BrickHouse {

    private BuildingBlock buildingBlock;

    /**
     * теплопотери, Вт;
     * https://bilux.ua/raschet-teplopoter-chastnogo-doma/#
     */
    private double heatLoss(double tIn, double tOut) {
        return heatLossViaWall(tIn, tOut);
    }

    private double heatLossViaWall(double tIn, double tOut) {
        return heatTransferRatio() * wallSquare() * (tIn - tOut);
    }

    private double wallSquare() {
        return 10.0 * 7.0 * 4.0;
    }

    private double heatTransferRatio() {
        double wallThickness = buildingBlock.getLength(); // M
        double heatTransferRatio = buildingBlock.getHeatTransferRatio(); // Вт/м * C
        double rWall = wallThickness / heatTransferRatio;

        return 1.0 / rWall;
    }

    public static void main(String[] args) {
        BrickHouse house = new BrickHouse(BuildingBlock.GAS_CONCRETE_BLOCK_D300);
        double heatLoss = house.heatLoss(20.0, -20.0);
        log.info("Heat loss is {} KWt on wall square {} M2", heatLoss / 1000.0,
                house.wallSquare());
    }

    @Getter
    @AllArgsConstructor
    private enum BuildingBlock {
        CERAMICS_BRICK(0.7, 0.25, 0.12, 0.65),
        GAS_CONCRETE_BLOCK_D300(0.117, 0.6, 0.3, 0.2)
        ;

        private final double heatTransferRatio;

        private final double length;
        private final double width;
        private final double height;

    }
}
