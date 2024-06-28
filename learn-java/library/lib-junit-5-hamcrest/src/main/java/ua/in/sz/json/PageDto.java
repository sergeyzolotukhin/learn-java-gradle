package ua.in.sz.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor(staticName = "of")
@Setter
@JsonFilter("myFilter")
//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class PageDto {
	private String title;
	private String content;
	private int size;

	public PageDto() {
		log.info("PageDto constructor");
	}

	public boolean equals(final Object o) {
		log.info("equals called");

		if (o == this) return true;
		if (!(o instanceof PageDto)) return false;
		final PageDto other = (PageDto) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$title = this.getTitle();
		final Object other$title = other.getTitle();
		if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
		final Object this$content = this.getContent();
		final Object other$content = other.getContent();
		if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof PageDto;
	}

	public int hashCode() {
		log.info("hashCode called");

		final int PRIME = 59;
		int result = 1;
		final Object $title = this.getTitle();
		result = result * PRIME + ($title == null ? 43 : $title.hashCode());
		final Object $content = this.getContent();
		result = result * PRIME + ($content == null ? 43 : $content.hashCode());
		return result;
	}

	public String toString() {
		return "PageDto(title=" + this.getTitle() + ", content=" + this.getContent() + ")";
	}
}
