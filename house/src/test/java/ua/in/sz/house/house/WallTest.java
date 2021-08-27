package ua.in.sz.house.house;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.in.sz.house.material.calculator.CementMortarCalculator;
import ua.in.sz.house.transport.Place;

@Slf4j
class WallTest {

    @Test
    void cementMortar() {
        Wall wall = Wall.builder().block(Block.CERAMICS_BRICK).height(1.0).length(1.0).build();
        House house = new House(Place.VORZEL, null, wall, 0, 0, 0);

        double expected = 0.106;
        double actual = CementMortarCalculator.calculate(house).getQuantity();

        log.trace(String.format("Expected %.3f M3 actual %.3f", expected, actual));

        Assertions.assertEquals(expected, actual, 0.001);
    }
}