package ua.in.sz.bean.validation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.Diff;
import org.apache.commons.lang3.builder.DiffResult;
import org.apache.commons.lang3.builder.ReflectionDiffBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ua.in.sz.bean.validation.model.Rect;
import ua.in.sz.bean.validation.model.Sphera;

@Slf4j
public class Application {
    public static void main(String[] args) {
        Rect rectA = Rect.builder()
                .x(-10)
                .y(-15)
                .height(2000)
                .width(-1)
                .sphera(Sphera.builder().radius(1).build())
                .build();
        Rect rectB = Rect.builder()
                .x(-11)
                .y(-116)
                .height(20050)
                .width(-1)
                .sphera(Sphera.builder().radius(1).build())
                .build();

        ReflectionDiffBuilder<Rect> builder = new ReflectionDiffBuilder<>(rectA, rectB, ToStringStyle.JSON_STYLE);
        DiffResult<Rect> diffResult = builder.build();

        log.info("Diff: {}", diffResult.toString());

        for (Diff<?> field : diffResult.getDiffs()) {
            log.info("{} : {} is {}",
                    field.getFieldName(),
                    field.getLeft(),
                    field.getRight());
        }
    }
}
