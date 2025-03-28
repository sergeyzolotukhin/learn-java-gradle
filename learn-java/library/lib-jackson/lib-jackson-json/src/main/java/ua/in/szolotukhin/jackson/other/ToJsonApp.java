package ua.in.szolotukhin.jackson.other;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.other.json.MapperFactory;
import ua.in.szolotukhin.jackson.other.model.SchedulesFilter;
import ua.in.szolotukhin.jackson.other.model.SchedulesRowDataProvider;

import java.io.IOException;

import static java.util.Collections.singletonList;

@Slf4j
public class ToJsonApp {

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = MapperFactory.createMapper();

		SchedulesFilter filter = SchedulesFilter.builder()
				.scheduleTypes(singletonList("RTBM"))
				.activeVersion(true)
				.build();

		SchedulesRowDataProvider dataProvider = SchedulesRowDataProvider.builder()
				.filter(filter)
				.build();

		String result = mapper.writeValueAsString(dataProvider);

		log.info("{}", result);
	}
}
