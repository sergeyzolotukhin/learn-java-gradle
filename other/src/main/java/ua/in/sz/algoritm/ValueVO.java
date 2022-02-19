package ua.in.sz.algoritm;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Slf4j
@Getter
public class ValueVO<T> {
	public static final DateTimeFormatter FORMAT = DateTimeFormat.forPattern("dd.MM.yy HH:mm");

	private DateTime start;
	private DateTime end;

	private T value;

	ValueVO(DateTime start, DateTime end, T value) {
		this.start = start;
		this.end = end;
		this.value = value;
	}

	public static <T> ValueVOBuilder<T> builder() {
		return new ValueVOBuilder<T>();
	}

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

	public static class ValueVOBuilder<T> {
		private DateTime start;
		private DateTime end;
		private T value;

		ValueVOBuilder() {
		}

		public ValueVOBuilder<T> start(int h, int m) {
			start(date(h, m));
			return this;
		}

		public ValueVOBuilder<T> start(DateTime start) {
			this.start = start;
			return this;
		}

		public ValueVOBuilder<T> end(int h, int m) {
			end(date(h, m));
			return this;
		}

		public ValueVOBuilder<T> end(DateTime end) {
			this.end = end;
			return this;
		}

		public ValueVOBuilder<T> value(T value) {
			this.value = value;
			return this;
		}

		public ValueVO<T> build() {
			return new ValueVO<T>(start, end, value);
		}

		public String toString() {
			return "ValueVO.ValueVOBuilder(start=" + this.start + ", end=" + this.end + ", value=" + this.value + ")";
		}
	}

	private static DateTime date(int h, int m) {
		return new DateTime(2020, 1, 1, h, m);
	}
}
