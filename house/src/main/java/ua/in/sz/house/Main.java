package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.house.Block;
import ua.in.sz.house.house.House;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.calculator.MaterialCalculator;
import ua.in.sz.house.shop.MaterialShop;
import ua.in.sz.house.shop.order.MaterialOrder;
import ua.in.sz.house.shop.order.OrderReport;
import ua.in.sz.house.transport.CarDepot;
import ua.in.sz.house.transport.Cars;
import ua.in.sz.house.transport.Place;

import java.util.List;

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

        Material materials = MaterialCalculator.calculate(house);
        List<MaterialOrder> order = MaterialShop.order(materials);
        log.info(OrderReport.report(order));

        for (MaterialOrder materialOrder : order) {
            double cost = CarDepot.cost(Cars.isuzuNqr75(), materialOrder);
            log.info("Transport of {} has cost {}", materialOrder.materialType(), cost);
        }
    }
}
