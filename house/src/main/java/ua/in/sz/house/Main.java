package ua.in.sz.house;

import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.boiler.impl.ElectricityBoiler;
import ua.in.sz.house.house.Block;
import ua.in.sz.house.house.House;
import ua.in.sz.house.material.BillOfMaterial;
import ua.in.sz.house.material.BillOfMaterialItem;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.material.calculator.MaterialCalculator;
import ua.in.sz.house.shop.order.MaterialOrderReport;
import ua.in.sz.house.shop.order.MaterialOrder;
import ua.in.sz.house.shop.MaterialShop;
import ua.in.sz.house.transport.Cars;
import ua.in.sz.house.transport.Distances;
import ua.in.sz.house.transport.TransportCostCalculator;

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

        BillOfMaterial materials = MaterialCalculator.calculate(house);
        List<MaterialOrder> order = MaterialShop.order(materials);

        Material cementMortar = materials.get(MaterialType.CEMENT_MORTAR);
        log.info("Cement mortar {}", cementMortar.getQuantity());

        log.info(MaterialOrderReport.report(order));

        TransportCostCalculator transportCostCalculator = TransportCostCalculator.of(Cars.dafXf95(), Distances.brickStockToVorzel());
        for (MaterialOrder materialOrder : order) {
            double cost = transportCostCalculator.cost(materialOrder);
            log.info("Transport of {} has cost {}", materialOrder.getMaterialType(), cost);
        }
    }
}
