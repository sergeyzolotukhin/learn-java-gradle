package ua.in.sz.house.material;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class BillOfMaterial implements BillOfMaterialItem {
    @Getter
    private final Material material;
    private final List<BillOfMaterialItem> children = new ArrayList<>();

    public BillOfMaterial(Material material) {
        this.material = material;
    }

    public Material get(MaterialType materialType) {
        if (material != null && materialType.equals(material.getMaterialType())) {
            return material;
        }

        for (BillOfMaterialItem child : children) {
            if (child instanceof Material && materialType.equals(((Material) child).getMaterialType())) {
                return (Material) child;
            }

            if (child instanceof BillOfMaterial) {
                Material item = ((BillOfMaterial) child).get(materialType);
                if (item != null) {
                    return item;
                }
            }
        }

        return null;
    }

    public void add(BillOfMaterialItem item) {
        children.add(item);
    }
}
