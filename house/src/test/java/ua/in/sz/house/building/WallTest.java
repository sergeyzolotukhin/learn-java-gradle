package ua.in.sz.house.building;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class WallTest {

    @Test
    void cementMortar() {
        Block block = Block.CERAMICS_BRICK;

        Wall wall = Wall.builder()
                .block(block)
                .height(1.0)
                .length(1.0)
                .build();

        double square = wall.getSquare();
        double v = wall.getWidth() + wall.getLength() * wall.getWidth();
        double e = v * 0.245; // Расход раствора (кубометр) для кладки в два керамический кирпича

        double vb = block.getLength() * block.getHeight() * block.getWidth();
        double vab = vb * wall.blockCount();

        double cementMortar = wall.cementMortar();
        log.info(String.format("Cement mortar %.2f", v - vab));
        log.info(String.format("Cement mortar: %.2f M3 expected %.2f M3", cementMortar, e));

        log.info(String.format("square %.2f M2 width %.2f M block count %.0f", square, wall.getWidth(), wall.blockCount()));
    }
}