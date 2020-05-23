package ua.in.sz.tabular.domain;


import java.io.Serializable;

public interface IdBean<T extends Serializable> extends Serializable {
    T getId();
}
