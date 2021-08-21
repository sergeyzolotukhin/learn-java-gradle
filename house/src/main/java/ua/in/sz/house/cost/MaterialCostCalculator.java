package ua.in.sz.house.cost;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ua.in.sz.house.material.AllMaterialCalculator;
import ua.in.sz.house.material.Material;

import java.util.List;

@Slf4j
@AllArgsConstructor(staticName = "of")
public class MaterialCostCalculator {
    private final List<Material> materials;

    public double blockCost() {
        return quantity(Material.Names.BRICK) * 3.3;
    }

    public double cementCost() {
        return quantity(Material.Names.CEMENT) / 1000.0 * 1800.0;
    }

    public double sangCost() {
        return quantity(Material.Names.SANG) / 1000.0 * 180.0;
    }

    private Double quantity(Material.Names materialName) {
        return materials.stream()
                .filter(m -> materialName.equals(m.getName()))
                .map(Material::getQuantity)
                .findFirst()
                .orElse(0.0);
    }
}
