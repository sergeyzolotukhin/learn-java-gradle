package ua.in.sz.house;

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
        return (10.0 + 10.0 + 10.0 + 10.0) * 2.75;
    }

    private double heatTransferRatio() {
        double wallThickness = 0.120 * 2.0 + 0.010;
        double brickHeatTransfer = 0.7; // Вт/м * К
        double aIn = 8.7;
        double aOut = 23.0;
        return 1.0 / (1.0 / aIn + wallThickness / brickHeatTransfer + 1.0 / aOut);
    }

    public static void main(String[] args) {
        BrickHouse house = new BrickHouse();
        double heatLoss = house.heatLoss(23.0, 15.0);
        log.info("Heat loss is {} KWt", heatLoss / 1000.0);
    }
}
