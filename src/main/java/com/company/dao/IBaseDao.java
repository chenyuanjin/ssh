package com.company.dao;

import com.company.entity.Pagers;

import java.util.List;

/**
 * Created by chenyj on 16/1/13.
 */
public interface IBaseDao<T> {
    T add(T t);

    void update(T t);

    void delete(int id);

    T load(int id);

    void saveOrUpdate(T t);

    T get(int id);

    List<T> list(String hql, Object[] args, String sort, boolean desc);

    List<T> list(String hql, Object[] args);

    List<T> list(String hql);

    Pagers<T> find(String hql, Object[] args, String sort, boolean desc, int pageSize, int offset);

    Pagers<T> find(String hql, Object[] args, int pageSize, int offset);

    Pagers<T> find(String hql, int PageSize, int offset);

    void update(String hql, Object[] args);

    void update(String hql);

    T queryObject(String hql, Object[] args);

    T queryObject(String hql);

    List<T> listBySql(String sql, Object[] args, String sort, boolean desc);

    List<T> listBySql(String sql, Object[] args);

    List<T> listBySql(String sql);

    Pagers<T> findBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity, String sort, boolean desc, int pageSize, int offset);

    Pagers<T> findBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity, int pageSize, int offset);

    Pagers<T> findBySql(String sql, Class<?> clz, boolean hasEntity, int PageSize, int offset);

    Object queryObjectBySql(String sql, Object[] args);

    Object queryObjectBySql(String sql);


    void executeSql(String sql);

    void executeSql(String sql, Object[] agrs);
}
