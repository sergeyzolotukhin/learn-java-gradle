package ua.in.sz.logging.logs;

import lombok.Builder;
import lombok.ToString;

@ToString
@Builder
public class Task {
    private String name;
    private String description;
    private int number;
}
