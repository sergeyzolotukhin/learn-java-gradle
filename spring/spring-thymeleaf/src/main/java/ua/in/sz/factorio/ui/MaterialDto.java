package ua.in.sz.factorio.ui;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class MaterialDto {
    private final String id;
    private final String icon;
    private final int time;

    @Builder.Default
    private final List<RequiredDto> required = new ArrayList<>();

    public String getRequiredIcon(int i) {
        return i < required.size() ? required.get(i).icon : null;
    }

    public Integer getRequiredAmount(int i) {
        return i < required.size() ? required.get(i).amount : null;
    }

    @Builder
    public static class RequiredDto {
        private final String icon;
        private final int amount;
    }
}
