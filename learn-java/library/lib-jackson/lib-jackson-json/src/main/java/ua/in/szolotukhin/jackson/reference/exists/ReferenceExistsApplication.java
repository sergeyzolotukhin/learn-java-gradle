package ua.in.szolotukhin.jackson.reference.exists;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.reference.exists.container.Model;
import ua.in.szolotukhin.jackson.reference.exists.model.School;
import ua.in.szolotukhin.jackson.reference.exists.model.Student;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class ReferenceExistsApplication {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = createMapper();

        writeModel(mapper);
        readModel(mapper);
    }

    private static void readModel(ObjectMapper mapper) throws JsonProcessingException {
        String input = """
{
  "schools" :
  [
      {
        "name" : "St Magdalene's",
        "id" : 1
      }
  ],
  "students" :
  [
      {
        "name" : "Mary",
        "id" : 1,
        "school" : "St Magdalene's"
      },
      {
        "name" : "Bob",
        "id" : 2,
        "school" : "St Magdalene's"
      }
  ]
}
                """;

        Model out = mapper.readValue(input, Model.class);
        log.info("Our students: {}", out.getStudents());
    }

    private static void writeModel(ObjectMapper mapper) throws JsonProcessingException {
        Model model = createModel();
        String serialized = mapper.writeValueAsString(model);
        log.info("Serialized: \n{}", serialized);
    }

    private static Model createModel() {
        School school = new School(1, "St Magdalene's");
        Student mary = new Student(1, "Mary", school);
        Student bob = new Student(2, "Bob", school);
        Student[] students = {mary, bob};

        return new Model(List.of(school), Arrays.asList(students));
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper;
    }
}

