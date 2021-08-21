package ua.in.sz.house.material;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(staticName = "of")
@ToString
public class Material {
    private final String name;
    private final double quantity;
}
