package ua.in.sz.jaxb.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

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
    private List<Page> pages;
}
