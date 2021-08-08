package ua.in.sz.house.boiler.impl;

import lombok.NoArgsConstructor;
import ua.in.sz.house.boiler.Boiler;
import ua.in.sz.house.boiler.Resource;
import ua.in.sz.house.building.House;

@NoArgsConstructor
public class ElectricityBoiler extends Boiler {
    @Override
    public Resource resourceQuantity(House house, double tIn, double tOut) {
        return Resource.Electricity.of(house.getHeatLoss(tIn, tOut));
    }
}