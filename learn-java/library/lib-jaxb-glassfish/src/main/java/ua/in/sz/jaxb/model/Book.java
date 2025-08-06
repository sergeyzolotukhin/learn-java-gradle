package ua.in.sz.jaxb.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@XmlType
@XmlRootElement
public class Book {
//    @XmlAttribute
    private Long id;
//    @XmlElement
    private String name;
//    @XmlTransient
    private String author;
    private Date date;
}
