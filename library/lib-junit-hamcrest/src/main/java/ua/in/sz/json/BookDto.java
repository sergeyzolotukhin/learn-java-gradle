package ua.in.sz.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Getter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Setter
public class BookDto {
	private String name;
	private Date date;
}
