package ua.in.szolotukhin.jackson.reference.exists.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.in.szolotukhin.jackson.reference.exists.model.School;
import ua.in.szolotukhin.jackson.reference.exists.model.Student;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Model {
    private List<School> schools = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
}
