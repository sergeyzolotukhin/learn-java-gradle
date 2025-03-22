package ua.in.szolotukhin.jackson.non.standard.setter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ua.in.szolotukhin.jackson.non.standard.setter.modle.Student;

@Slf4j
public class NonStandardSetterApp {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = """
                {
                    "rollNo":1,
                    "name":"Marks"
                }
                """;


        Student student = mapper.readerFor(Student.class).readValue(json);
        log.info("Name: [{}]", student.name);
    }
}
