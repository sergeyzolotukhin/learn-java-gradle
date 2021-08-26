package ua.in.sz.house.shop;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import ua.in.sz.house.material.BillOfMaterial;
import ua.in.sz.house.material.Material;
import ua.in.sz.house.material.MaterialType;
import ua.in.sz.house.shop.order.MaterialOrder;
import ua.in.sz.house.shop.order.MaterialPackageOrder;
import ua.in.sz.house.shop.order.MaterialUnPackageOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaterialShop {
    private static final Map<MaterialType, Double> costs = ImmutableMap.<MaterialType, Double>builder()
            .put(MaterialType.BRICK, 3.3 * 420)
            .put(MaterialType.CEMENT, 1800.0 / 1000.0 * 50)
            .put(MaterialType.SANG, 180.0 / 1000.0)
            .build();

    private static final Map<MaterialType, MaterialPackage> packages = ImmutableMap.<MaterialType, MaterialPackage>builder()
            .put(MaterialType.BRICK, MaterialPackages.brickPackage())
            .put(MaterialType.CEMENT, MaterialPackages.cementPackage())
            .build();

    private static final List<MaterialType> unPackages = ImmutableList.<MaterialType>builder()
            .add(MaterialType.SANG)
            .build();

    public static List<MaterialOrder> order(BillOfMaterial materials) {
        List<MaterialOrder> result = new ArrayList<>();

        Material brick = materials.get(MaterialType.BRICK);
        MaterialPackage brickPackage = MaterialPackages.brickPackage();
        double brickPackCount = Math.ceil(brick.getQuantity() / brickPackage.getCount());
        double brickCost = cost(MaterialType.BRICK, brickPackCount);
        result.add(MaterialPackageOrder.of(brickPackage, brickPackCount, brickCost));

        Material cement = materials.get(MaterialType.CEMENT);
        MaterialPackage cementPackage = MaterialPackages.cementPackage();
        double cementPackCount = Math.ceil(cement.getQuantity() / brickPackage.getCount());
        double cementCost = cost(MaterialType.CEMENT, cementPackCount);
        result.add(MaterialPackageOrder.of(cementPackage, cementPackCount, cementCost));

        Material sang = materials.get(MaterialType.SANG);
        double sangCost = cost(MaterialType.SANG, sang.getQuantity());
        result.add(MaterialUnPackageOrder.of(MaterialType.SANG, sang.getQuantity(), sangCost));

        return result;
    }

    private static double cost(MaterialType materialType, double quantity) {
        Double cost = costs.get(materialType);
        if (cost == null) {
            throw new IllegalStateException("The cost of material [" + materialType + "] not found");
        }

        return quantity * cost;
    }
}
