package ua.in.sz.factorio.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Material {
    private String id;
    private String name;
    private String icon;
    /**
     * craft time in seconds
     */
    private int time;
    /**
     * Required resources
     */
    @Builder.Default
    private List<RequiredMaterial> required = new ArrayList<>();
}
