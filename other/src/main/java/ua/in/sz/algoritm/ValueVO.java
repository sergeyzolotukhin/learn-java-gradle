package ua.in.sz.algoritm;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;

@Slf4j
@Getter
@Builder
public class ValueVO<T> {
	public static final DateTimeFormatter FORMAT = DateTimeFormat.forPattern("dd.MM.yy HH:mm");

	private DateTime start;
	private DateTime end;

	private T value;

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder("Value ")
				.append(FORMAT.print(start))
				.append(" - ")
				.append(FORMAT.print(end))
				.append(" = ")
				.append(value);
		return sb.toString();
	}
}
