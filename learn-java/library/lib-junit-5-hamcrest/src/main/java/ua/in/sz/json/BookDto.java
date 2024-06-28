package ua.in.sz.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor(staticName = "of")
@Setter
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BookDto {
	private String name;
	private Date date;
	private List<PageDto> pages = Arrays.asList(
			PageDto.of("Title-1", "Text-1"),
			PageDto.of("Title-2", "Text-2")
	);

	public BookDto() {
		log.info("BookDto constructor");
	}

	public boolean equals(final Object o) {
		log.info("equals call");

		if (o == this) return true;
		if (!(o instanceof BookDto)) return false;
		final BookDto other = (BookDto) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$name = this.getName();
		final Object other$name = other.getName();
		if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
		final Object this$date = this.getDate();
		final Object other$date = other.getDate();
		if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
		final Object this$pages = this.getPages();
		final Object other$pages = other.getPages();
		if (this$pages == null ? other$pages != null : !this$pages.equals(other$pages)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof BookDto;
	}

	public int hashCode() {
		log.info("hashCode call");

		final int PRIME = 59;
		int result = 1;
		final Object $name = this.getName();
		result = result * PRIME + ($name == null ? 43 : $name.hashCode());
		final Object $date = this.getDate();
		result = result * PRIME + ($date == null ? 43 : $date.hashCode());
		final Object $pages = this.getPages();
		result = result * PRIME + ($pages == null ? 43 : $pages.hashCode());
		return result;
	}

	public String toString() {
		return "BookDto(name=" + this.getName() + ", date=" + this.getDate() + ", pages=" + this.getPages() + ")";
	}
}
