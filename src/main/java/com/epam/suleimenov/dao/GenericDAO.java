package com.epam.suleimenov.dao;

import java.util.List;

public interface GenericDAO<T> {

    boolean clear();

    T create(T t);

    boolean delete(Object id);

    T find(Object id);

    List<T> getAll();

    T update(T t);
}
