package ua.in.sz.house.material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BillOfMaterials<T extends Material> extends ArrayList<T> {
    private BillOfMaterials(Collection<T> c) {
        super(c);
    }

    public T get(MaterialType materialType) {
        return this.stream()
                .filter(m -> materialType.equals(m.getMaterialType()))
                .findFirst()
                .orElse(null);
    }

    public static <E extends Material> BillOfMaterials<E> of(List<E> materials) {
        return new BillOfMaterials<E>(materials);
    }

}
