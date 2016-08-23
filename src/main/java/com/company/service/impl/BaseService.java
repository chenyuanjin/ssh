package com.company.service.impl;


import com.company.dao.impl.BaseDao;
import com.company.service.IBaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;

/**
 * Created by chenyuanjin on 15/6/19.
 */
@Service
public class BaseService<T> implements IBaseService<T> {

    private static Logger logger = Logger.getLogger(BaseService.class);

    @Autowired
    BaseDao<T> baseDao;
    /**
     * 创建一个Class的对象来获取泛型的class
     */


    private Class<?> clz;

    public Class<?> getClz() {
        if (clz == null) {
            //获取泛型的Class对象
            clz = ((Class<?>)
                    (((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }

    @Override
    public void add(T t) {
        baseDao.add(t);
    }

    @Override
    public void update(T t) {

        baseDao.update(t);
    }

    @Override
    public void delete(int id) {
        baseDao.delete(id);
    }

    @Override
    public T load(int id) {
        return baseDao.load(id);
    }

    @Override
    public T get(int id) {
        return baseDao.get(id);
    }

    @Override
    public void saveOrUpdate(T t) {
        baseDao.saveOrUpdate(t);
    }

}
