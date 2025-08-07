package ua.in.sz.oxm.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student{
    String name;
    int age;
    int id;

    public String getName(){
        return name;
    }
    @XmlElement
    public void setName(String name){
        this.name = name;
    }
    public int getAge(){
        return age;
    }
    @XmlElement
    public void setAge(int age){
        this.age = age;
    }
    public int getId(){
        return id;
    }
    @XmlAttribute
    public void setId(int id){
        this.id = id;
    }
}