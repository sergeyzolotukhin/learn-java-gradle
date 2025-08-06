package ua.in.sz.jaxb.model;

import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.in.sz.jaxb.model.page.FirstPage;
import ua.in.sz.jaxb.model.page.SecondPage;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@XmlType
@XmlRootElement
public class Book {
    private Long id;
    private String name;
    private String author;
    private Date date;

    private List<Page> pages;

/*    @XmlElements({
            @XmlElement(name="page", type=Page.class),
            @XmlElement(name="firstPage", type= FirstPage.class),
            @XmlElement(name="secondPage", type= SecondPage.class)
    })*/
    @XmlAnyElement(lax = true)
    public List<Page> getPages() {
        return pages;
    }
}
