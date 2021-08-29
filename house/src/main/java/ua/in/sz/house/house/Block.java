package ua.in.sz.house.house;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Block {
    /**
     * Полнотелый кирамический кирпич
     */
    CERAMICS_BRICK(0.7, 0.25, 0.12, 0.065, 3.4),
    /**
     * Газоблок
     */
    GAS_CONCRETE_BLOCK_D300(0.088, 0.6, 0.3, 0.2, 10.8),
    GAS_CONCRETE_BLOCK_D400(0.117, 0.6, 0.3, 0.2, 14.4),
    GAS_CONCRETE_BLOCK_D500(0.147, 0.6, 0.3, 0.2, 18),
    ;

    private final double heatTransferRatio;

    private final double length;
    private final double width;
    private final double height;

    private final double weight;

}
