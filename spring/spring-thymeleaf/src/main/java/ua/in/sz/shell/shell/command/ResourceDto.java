package ua.in.sz.shell.shell.command;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResourceDto {
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String icon;
}
