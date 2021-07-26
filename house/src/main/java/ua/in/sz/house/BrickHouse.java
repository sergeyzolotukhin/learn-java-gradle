package ua.in.sz.house;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrickHouse {
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
        int nBrick = 2;
//        double wallThickness = 0.250 * nBrick + 0.010 * (nBrick - 1); // M
        double wallThickness = 0.5; // M
        double heatTransferRatio = Material.GAS_CONCRETE_D300.getHeatTransferRatio(); // Вт/м * C
        double rWall = wallThickness / heatTransferRatio;

        return 1.0 / rWall;
    }

    public static void main(String[] args) {
        BrickHouse house = new BrickHouse();
        double heatLoss = house.heatLoss(20.0, -20.0);
        log.info("Heat loss is {} KWt on wall square {} M2", heatLoss / 1000.0,
                house.wallSquare());
    }

    @Getter
    @AllArgsConstructor
    private enum Material {
        /**
         * Кирпичь полнотелый
         */
        CERAMICS(0.7),
        GAS_CONCRETE_D300(0.117)
        ;

        /**
         * Коэффициент теплопроводности, Вт/м °C
         */
        private final double heatTransferRatio;
    }
}
