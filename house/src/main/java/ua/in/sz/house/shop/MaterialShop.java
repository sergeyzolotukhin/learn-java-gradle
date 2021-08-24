package ua.in.sz.house.shop;

import com.google.common.collect.ImmutableMap;
import ua.in.sz.house.material.BillOfMaterials;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialCode;
import ua.in.sz.house.shop.order.MaterialOrder;
import ua.in.sz.house.shop.order.MaterialPackageOrder;
import ua.in.sz.house.shop.order.MaterialUnPackageOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaterialShop {
    private static final Map<String, Double> costs = ImmutableMap.<String, Double>builder()
            .put(MaterialCode.BRICK.code(), 3.3)
            .put(MaterialCode.CEMENT.code(), 1800.0 / 1000.0)
            .put(MaterialCode.CEMENT_MORTAR.code(), 0.0)
            .put(MaterialCode.SANG.code(), 180.0 / 1000.0)
            .build();

    public static List<MaterialOrder> order(BillOfMaterials<Material> materials) {
        List<MaterialOrder> result = new ArrayList<>();

        Material brick = materials.get(MaterialCode.BRICK);
        MaterialPackage brickPackage = MaterialPackages.brickPackage();
        double brickPackCount = Math.ceil(brick.getQuantity() / brickPackage.getCount());
        double brickCost = cost(MaterialCode.BRICK, brickPackCount);
        result.add(MaterialPackageOrder.of(brickPackage, brickPackCount, brickCost));

        Material cement = materials.get(MaterialCode.CEMENT);
        MaterialPackage cementPackage = MaterialPackages.cementPackage();
        double cementPackCount = Math.ceil(cement.getQuantity() / brickPackage.getCount());
        double cementCost = cost(MaterialCode.CEMENT, cementPackCount);
        result.add(MaterialPackageOrder.of(cementPackage, cementPackCount, cementCost));

        Material sang = materials.get(MaterialCode.SANG);
        double sangCost = cost(MaterialCode.SANG, sang.getQuantity());
        result.add(MaterialUnPackageOrder.of(MaterialCode.SANG, sang.getQuantity(), sangCost));

        return result;
    }

    private static double cost(MaterialCode materialCode, double quantity) {
        Double cost = costs.get(materialCode.code());
        if (cost == null) {
            throw new IllegalStateException("The cost of material [" + materialCode.code() + "] not found");
        }

        return quantity * cost;
    }
}
