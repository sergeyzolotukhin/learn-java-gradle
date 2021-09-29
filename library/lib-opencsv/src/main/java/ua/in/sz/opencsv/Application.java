package ua.in.sz.opencsv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.FuzzyMappingStrategy;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;


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

        try (Scanner scanner = new Scanner(path)) {
			while (scanner.hasNext()) {
				String line = scanner.next();
				log.info("{}", line);
			}
		}
    }

    private static CsvToBean<Employee> csvToEmployee(Reader reader) {
        return new CsvToBeanBuilder<Employee>(reader)
                .withType(Employee.class)
                .withMappingStrategy(employeeMappingStrategy())
                .build();
    }

    private static MappingStrategy<Employee> employeeMappingStrategy() {
		HeaderColumnNameMappingStrategy<Employee> ms = new HeaderColumnNameMappingStrategy<>();
        ms.setType(Employee.class);
        return ms;
    }
}
