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

    public BillOfMaterialItem get(MaterialType materialType) {
        for (BillOfMaterialItem child : children) {
            if (materialType.equals(child.getMaterialType())) {
                return child;
            }

            if (child instanceof BillOfMaterial) {
                BillOfMaterialItem item = ((BillOfMaterial) child).get(materialType);
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

    @Override
    public MaterialType getMaterialType() {
        return parent.getMaterialType();
    }
}
