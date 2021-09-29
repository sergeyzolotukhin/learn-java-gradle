package ua.in.sz.opencsv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.FuzzyMappingStrategy;
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
			List<Employee> employees = csvToEmployee(reader).parse();

			for (Employee employee : employees) {
				log.info("{}", employee);
			}
		}
	}

	private static CsvToBean<Employee> csvToEmployee(Reader reader) {
		return new CsvToBeanBuilder<Employee>(reader)
				.withType(Employee.class)
				.withMappingStrategy(employeeMappingStrategy())
				.build();
	}

	private static FuzzyMappingStrategy<Employee> employeeMappingStrategy() {
		FuzzyMappingStrategy<Employee> ms = new FuzzyMappingStrategy<>();
		ms.setType(Employee.class);
		return ms;
	}
}
