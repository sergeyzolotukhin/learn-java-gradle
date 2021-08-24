package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.building.Block;
import ua.in.sz.house.building.House;
import ua.in.sz.house.cost.TransportCostCalculator;
import ua.in.sz.house.material.*;
import ua.in.sz.house.material.calculator.MaterialCalculator;
import ua.in.sz.house.report.MaterialOrderReport;
import ua.in.sz.house.shop.MaterialOrder;
import ua.in.sz.house.shop.MaterialShop;
import ua.in.sz.house.transport.Cars;
import ua.in.sz.house.transport.Distances;

import java.util.List;

@Slf4j
public class Main {
    public static final double TARGET_TEMPERATURE = 23.0;

    public static void main(String[] args) {
        House house = House.builder()
                .block(Block.CERAMICS_BRICK)
                .boiler(new ElectricityBoiler())
                .size(10.0, 10.0, 3.0)
                .build();

        BillOfMaterials<Material> materials = MaterialCalculator.calculate(house);
        List<MaterialOrder> order = MaterialShop.order(materials);

        TransportCostCalculator transportCostCalculator = TransportCostCalculator.of(Cars.dafXf95(), Distances.brickStockToTarasovo());

//        double brickTravelCost = transportCostCalculator.cost(pack, requiredPackageCount);

//        log.info(HouseReport.of(house).report());
        log.info(MaterialOrderReport.report(order));
//        log.info(SupportReport.of(house).report());
    }
}
