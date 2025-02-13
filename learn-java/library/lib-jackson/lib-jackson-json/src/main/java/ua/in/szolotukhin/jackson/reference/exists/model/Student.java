package ua.in.szolotukhin.jackson.reference.exists.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.in.szolotukhin.jackson.reference.exists.CustomSimpleObjectIdResolver;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonIdentityInfo(
        scope = Student.class,
        generator = ObjectIdGenerators.PropertyGenerator.class,
        resolver = CustomSimpleObjectIdResolver.class,
        property = "id")
public class Student {
    private Integer id;
    private String name;
    private School school;
}
