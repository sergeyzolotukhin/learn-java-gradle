package ua.in.sz.jaxb;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement
public class Employee {
    private int id;
    private String name;
    private float salary;
}