package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.house.Block;
import ua.in.sz.house.house.House;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.calculator.MaterialCalculator;
import ua.in.sz.house.shop.MaterialShop;
import ua.in.sz.house.shop.order.MaterialOrder;
import ua.in.sz.house.shop.order.MaterialOrderReport;
import ua.in.sz.house.transport.*;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        House house = House.builder()
                .place(Place.VORZEL)
                .block(Block.CERAMICS_BRICK)
                .boiler(new ElectricityBoiler())
                .size(10.0, 10.0, 3.0)
                .build();

        Material material = MaterialCalculator.calculate(house);
        MaterialOrder materialOrder = MaterialShop.makeOrder(material);
        log.info(MaterialOrderReport.report(materialOrder));

        CarOrder carOrder = CarDepot.makeOrder(materialOrder);
        CarPrice carPrice = CarDepot.makePrice(carOrder);
        log.info(CarPriceReport.report(carPrice));
    }
}
