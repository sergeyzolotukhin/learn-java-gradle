package ua.in.sz.jaxb.model.page;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.in.sz.jaxb.model.Page;

@ToString
@Getter
@Setter
@XmlType
@XmlRootElement
public class ThirdPage extends Page {
    private String comment;
}
