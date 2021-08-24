package ua.in.sz.house.boiler;

import ua.in.sz.house.house.House;

public abstract class Boiler {

    public abstract Resource resourceQuantity(House house, double tIn, double tOut);
}
