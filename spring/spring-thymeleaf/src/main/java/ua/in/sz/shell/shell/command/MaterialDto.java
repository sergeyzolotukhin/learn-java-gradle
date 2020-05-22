package ua.in.sz.shell.shell.command;

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

    public int getRequiredAmount(int i) {
        return i < required.size() ? required.get(i).amount : 0;
    }

    @Builder
    public static class RequiredDto {
        private final String icon;
        private final int amount;
    }
}
