package ua.in.sz.english.dict2json.model;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Word {
	private String text;

	private String word;
	private List<Definition> definitions = Lists.newArrayList();

	@Override
	public String toString() {
		return word;
	}
}
