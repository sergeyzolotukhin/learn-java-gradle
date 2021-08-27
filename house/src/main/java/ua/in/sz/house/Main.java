package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.house.Block;
import ua.in.sz.house.house.House;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.calculator.MaterialCalculator;
import ua.in.sz.house.shop.MaterialShop;
import ua.in.sz.house.shop.order.Order;
import ua.in.sz.house.shop.order.OrderReport;
import ua.in.sz.house.transport.Cars;
import ua.in.sz.house.transport.DistanceResolver;
import ua.in.sz.house.transport.Place;
import ua.in.sz.house.transport.TransportCostCalculator;

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
        List<Order> order = MaterialShop.order(materials);
        log.info(OrderReport.report(order));

        DistanceResolver distanceResolver = new DistanceResolver(house.getPlace(), Place.TRAVITA, Place.MOROR_M);
        for (Order materialOrder : order) {
            double cost = TransportCostCalculator.cost(Cars.dafXf95(), materialOrder, distanceResolver);
            log.info("Transport of {} has cost {}", materialOrder.materialType(), cost);
        }
    }
}
