package ua.in.sz.house.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Block {
    /**
     * Полнотелый кирамический кирпич
     */
    CERAMICS_BRICK(0.7, 0.25, 0.12, 0.65),
    /**
     * Газоблок марки D300
     */
    GAS_CONCRETE_BLOCK_D300(0.117, 0.6, 0.3, 0.2);

    private final double heatTransferRatio;

    private final double length;
    private final double width;
    private final double height;

}
