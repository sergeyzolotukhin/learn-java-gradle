package ua.in.sz.house.material;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(staticName = "of")
@ToString
public class Material {
    private final Names name;
    private final double quantity;

    public enum Names {
        BRICK,
        CEMENT,
        CEMENT_MORTAR,
        SANG
    }
}
