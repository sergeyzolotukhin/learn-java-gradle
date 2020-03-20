package ua.in.sz.javacore.stream;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Builder
@Getter
public class GroupDto {
	private String type;
	private LocalDate date;
}
