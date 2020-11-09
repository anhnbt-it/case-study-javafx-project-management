package com.anhnbt.services;

import java.util.List;

public interface IManagement<T> {
    List<T> getAll();
    T get(int id);
    boolean save(T obj);
    T update(int index, T obj);
    T delete(int index);
    boolean delete(T obj);
}
