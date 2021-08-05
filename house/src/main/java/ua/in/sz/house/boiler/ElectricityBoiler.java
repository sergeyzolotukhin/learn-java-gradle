package ua.in.sz.house.boiler;

import lombok.NoArgsConstructor;
import ua.in.sz.house.building.House;

@NoArgsConstructor(staticName = "of")
public class ElectricityBoiler extends Boiler {
    @Override
    public Resource resourceQuantity(House house, double tIn, double tOut) {
        return Resource.Electricity.of(house.getHeatLoss(tIn, tOut));
    }
}