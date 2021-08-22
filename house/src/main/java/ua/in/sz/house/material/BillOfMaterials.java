package ua.in.sz.house.material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BillOfMaterials extends ArrayList<Material> {
    private BillOfMaterials(Collection<? extends Material> c) {
        super(c);
    }

    public Material get(Material.Name name) {
        return this.stream()
                .filter(m -> name.equals(m.getName()))
                .findFirst()
                .orElse(null);
    }

    public static BillOfMaterials of(List<Material> materials) {
        return new BillOfMaterials(materials);
    }
}
