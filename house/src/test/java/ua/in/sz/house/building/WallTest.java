package ua.in.sz.house.building;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WallTest {

    @Test
    void cementMortar() {
        Wall wall = Wall.builder().block(Block.CERAMICS_BRICK).height(1.0).length(1.0).build();

        double expected = 0.106;
        double actual = wall.cementMortar();

        log.trace(String.format("Expected %.3f M3 actual %.3f", expected, actual));

        Assertions.assertEquals(expected, actual, 0.001);
    }
}