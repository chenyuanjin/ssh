package com.company.service;

/**
 * Created by chenyuanjin on 15/6/19.
 */
public interface IBaseService<T> {
    public void add(T t);

    public void update(T t);

    public void delete(int id);

    public T load(int id);

    public T get(int id);

    public void saveOrUpdate(T t);
}
