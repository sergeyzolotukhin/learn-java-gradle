package ua.in.sz.house.shop;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
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

    public static List<MaterialOrder> order(Material materials) {
        List<MaterialOrder> result = new ArrayList<>();

        for (Material m : materials.getAll()) {
            MaterialType type = m.getMaterialType();
            double quantity = m.getQuantity();

            if (packages.containsKey(type)) {
                result.add(packageOrder(type, quantity));
            } else if (unPackages.contains(type)) {
                result.add(unPackageOrder(type, quantity));
            }
        }

        return result;
    }

    private static MaterialUnPackageOrder unPackageOrder(MaterialType type, double quantity) {
        double cost = cost(type, quantity);
        return MaterialUnPackageOrder.of(type, quantity, cost);
    }

    private static MaterialPackageOrder packageOrder(MaterialType type, double quantity) {
        MaterialPackage pack = packages.get(type);
        double count = Math.ceil(quantity / pack.getCount());
        double cost = cost(type, quantity);
        return MaterialPackageOrder.of(pack, count, cost);
    }

    private static double cost(MaterialType materialType, double quantity) {
        Double cost = costs.getOrDefault(materialType, 0.0);
        return quantity * cost;
    }
}
