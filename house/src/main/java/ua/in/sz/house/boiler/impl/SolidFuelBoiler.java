package ua.in.sz.house.boiler.impl;

import lombok.AllArgsConstructor;
import ua.in.sz.house.boiler.Boiler;
import ua.in.sz.house.boiler.Resource;
import ua.in.sz.house.house.House;

@AllArgsConstructor
public class SolidFuelBoiler extends Boiler {
    /**
     * теплоемкость пеллет — 5 кВт/кг.
     */
    private static final double HEAT_CAPACITY_OF_PELLET = 4.73 * 1000.0;

    @Override
    public Resource resourceQuantity(House house, double tIn, double tOut) {
        double pelletPerHour = house.getHeatLoss(tIn, tOut) / HEAT_CAPACITY_OF_PELLET;
        return Resource.Pellet.of(pelletPerHour);
    }
}
