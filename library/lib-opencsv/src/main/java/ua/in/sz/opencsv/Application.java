package ua.in.sz.opencsv;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Slf4j
public class Application {
	@SneakyThrows
	public static void main(String[] args) {
		Path path = Paths.get(ClassLoader.getSystemResource("data.csv").toURI());
		try (Reader reader = Files.newBufferedReader(path)) {
			ColumnPositionMappingStrategy<Employee> ms = new ColumnPositionMappingStrategy<>();
			ms.setType(Employee.class);

			CsvToBean<Employee> cb = new CsvToBeanBuilder<Employee>(reader)
					.withType(Employee.class)
					.withMappingStrategy(ms)
					.build();

			List<Employee> employees = cb.parse();

			for (Employee employee : employees) {
				log.info("{}", employee);
			}
		}
	}
}
