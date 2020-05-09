package ua.in.sz.hibernate.xml.impl;


import java.io.Serializable;

public interface IdBean<T extends Serializable> extends Serializable {
    T getId();
}
