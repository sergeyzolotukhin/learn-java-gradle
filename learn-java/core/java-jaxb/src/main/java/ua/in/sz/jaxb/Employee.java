package ua.in.sz.jaxb;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    @XmlID
    private String id;

    @XmlAttribute
    private String name;

    @XmlIDREF
    private Employee manager;

    @XmlElement(name="report")
    @XmlIDREF
    private List<Employee> reports;

    public Employee() {
        reports = new ArrayList<>();
    }
}
