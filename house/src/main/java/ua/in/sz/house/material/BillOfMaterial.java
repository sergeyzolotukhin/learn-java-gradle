package ua.in.sz.house.material;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class BillOfMaterial implements BillOfMaterialItem {
    @Getter
    private final Material material;
    private final List<BillOfMaterialItem> children = new ArrayList<>();

    public BillOfMaterial(Material material) {
        this.material = material;
    }

    public Material get(MaterialType materialType) {
        return Optional.ofNullable(this.material)
                .filter(m -> materialType.equals(m.getMaterialType()))
                .or(() -> children.stream()
                        .filter(Material.class::isInstance)
                        .map(Material.class::cast)
                        .filter(m -> materialType.equals(m.getMaterialType()))
                        .findFirst())
                .or(() -> children.stream()
                        .filter(BillOfMaterial.class::isInstance)
                        .map(BillOfMaterial.class::cast)
                        .map(m -> m.get(materialType))
                        .filter(Objects::nonNull)
                        .findFirst())
                .orElse(null);
    }

    public void add(BillOfMaterialItem item) {
        children.add(item);
    }
}
