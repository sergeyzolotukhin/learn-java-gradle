package ua.in.sz.shell.shell.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResourceDto {
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
    private List<RequiredResourceDto> required;

    public RequiredResourceDto getRequired(int index) {
        return required != null && required.size() > index ? required.get(index) : new RequiredResourceDto();
    }
}
