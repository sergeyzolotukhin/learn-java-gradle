package ua.in.sz.json;

import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
public class PageDto {
	private String title;
	private String content;
}
