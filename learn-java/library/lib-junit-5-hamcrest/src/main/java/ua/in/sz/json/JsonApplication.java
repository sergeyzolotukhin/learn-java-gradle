package ua.in.sz.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

@Slf4j
public class JsonApplication {
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));

		BookDto bookDto = BookDto.of("Book - 1", new Date(), Collections.emptyList());
		log.info("to json:[{}]", mapper.writeValueAsString(bookDto));

		BookDto bookDto1 = mapper.readValue("{\"name\":\"Book - 1\",\"date\":\"11-11-2019\"}", BookDto.class);
		log.info("from json: [{}]", bookDto1.getDate());
	}

}
