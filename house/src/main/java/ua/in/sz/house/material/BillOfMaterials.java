package ua.in.sz.house.material;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BillOfMaterials<T extends HasMaterialCode> extends ArrayList<T> {
    private BillOfMaterials(Collection<T> c) {
        super(c);
    }

    public T get(MaterialCode materialCode) {
        return this.stream()
                .filter(m -> materialCode.code().equals(m.code()))
                .findFirst()
                .orElse(null);
    }

    public static <E extends HasMaterialCode> BillOfMaterials<E> of(List<E> materials) {
        return new BillOfMaterials<E>(materials);
    }

}
