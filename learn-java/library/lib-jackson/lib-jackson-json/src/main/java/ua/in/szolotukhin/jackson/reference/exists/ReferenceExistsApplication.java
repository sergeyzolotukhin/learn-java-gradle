package ua.in.szolotukhin.jackson.reference.exists;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.reference.exists.model.School;
import ua.in.szolotukhin.jackson.reference.exists.model.Student;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ReferenceExistsApplication {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = createMapper();

        Student[] students = createModel();

        String serialized = mapper.writeValueAsString(students);
        log.info("Serialized: \n{}", serialized);

        String input = """
[
    {
      "name" : "Mary",
      "id" : 1,
      "school" : {
        "name" : "St Magdalene's",
        "id" : 1
      }
    }, {
      "name" : "Bob",
      "id" : 2,
      "school" : "St Magdalene's"
    }
]
                """;

        List<Student> ourStudents = Arrays.asList(mapper.readValue(input, Student[].class));
        log.info("Our students: {}", ourStudents);
    }

    private static Student[] createModel() {
        School school = new School(1, "St Magdalene's");
        Student mary = new Student(1, "Mary", school);
        Student bob = new Student(2, "Bob", school);
        return new Student[]{mary, bob};
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}

