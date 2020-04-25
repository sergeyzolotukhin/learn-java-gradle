package ua.in.sz.english.dict2json.model;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class Definition {
	private static final int TO_STRING_MAX_MEANING = 3;
	private static final String TO_STRING_SEPARATOR = ", ";

	private int no;
	private String text;

	private String transcription;
	private String partOfSpeech;
	private List<String> meanings = Lists.newArrayList();

	@Override
	public String toString() {
		String result = meanings.stream().limit(TO_STRING_MAX_MEANING).collect(Collectors.joining(TO_STRING_SEPARATOR));
		return meanings.size() <= TO_STRING_MAX_MEANING ? result : result + " ... ";
	}
}
