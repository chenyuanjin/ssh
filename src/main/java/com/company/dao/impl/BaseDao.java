package com.company.dao.impl;


import com.company.dao.IBaseDao;
import com.company.entity.Pagers;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diaozhengxin on 2015/6/9.
 */
@Repository
public class BaseDao<T> implements IBaseDao<T> {

    private static Logger logger = Logger.getLogger(BaseDao.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

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

    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected final Session openSession() {
        return sessionFactory.openSession();
    }

    @Override
    public T add(T t) {
        getSession().save(t);
        return t;
    }

    @Override
    @Transactional
    public void update(T t) {
        t = (T) getSession().merge(t);
        getSession().update(t);
        // getSession().flush();
    }

    @Override
    public void delete(int id) {
        getSession().delete(this.load(id));
        // getSession().flush();
    }

    @Override
    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);

    }

    @Override
    public T get(int id) {
        return (T) getSession().get(getClz(), id);
    }

    @Override
    public T load(int id) {
        return (T) getSession().load(getClz(), id);
    }

    @Override
    public List<T> list(String hql, Object[] args, String sort, boolean desc) {

        hql = initSort(hql, sort, desc);
        Query query = getSession().createQuery(hql);
        setParameter(query, args);
        List<T> lists = query.list();
        if (lists == null) {
            lists = new ArrayList<>();
        }
        return lists;
    }

    @Override
    public List<T> list(String hql, Object[] args) {
        return list(hql, args, null, false);
    }

    @Override
    public List<T> list(String hql) {
        return list(hql, null);
    }

    @Override
    public Pagers<T> find(String hql, Object[] args, String sort, boolean desc, int pageSize, int offset) {
        String countHql = getCount(hql);
        hql = initSort(hql, sort, desc);

        Query countQuery = getSession().createQuery(countHql);
        Query query = getSession().createQuery(hql).setMaxResults(pageSize).setFirstResult(offset);

        setParameter(countQuery, args);
        setParameter(query, args);

        Pagers<T> pagers = new Pagers<T>();
        pagers.setPageSize(pageSize);
        pagers.setOffSet(offset);
        pagers.setTotal(Integer.parseInt(countQuery.uniqueResult().toString()));
        List<T> lists = query.list();
        if (lists == null) {
            lists = new ArrayList<>();
        }
        pagers.setDatas(lists);
        return pagers;
    }

    @Override
    public Pagers<T> find(String hql, Object[] args, int pageSize, int offset) {
        return find(hql, args, null, false, pageSize, offset);
    }

    @Override
    public Pagers<T> find(String hql, int pageSize, int offset) {
        return find(hql, null, pageSize, offset);
    }

    @Override
    public void update(String hql, Object[] args) {
        Query query = getSession().createQuery(hql);
        setParameter(query, args);
        query.executeUpdate();
    }

    @Override
    public void update(String hql) {
        update(hql, null);
    }

    @Override
    public T queryObject(String hql, Object[] args) {
        Query query = getSession().createQuery(hql);
        setParameter(query, args);
        return (T) query.uniqueResult();
    }

    @Override
    public T queryObject(String hql) {
        return queryObject(hql, null);
    }

    @Override
    public List<T> listBySql(String sql, Object[] args, String sort, boolean desc) {
        sql = initSort(sql, sort, desc);
        Query query = getSession().createSQLQuery(sql);
        setParameter(query, args);
        List<T> lists = query.list();
        if (lists == null) {
            return new ArrayList<>();
        } else {
            return lists;
        }

    }

    @Override
    public List<T> listBySql(String sql, Object[] args) {
        return listBySql(sql, args, null, false);
    }

    @Override
    public List<T> listBySql(String sql) {
        return listBySql(sql, null);
    }

    @Override
    public Pagers<T> findBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity, String sort, boolean desc, int pageSize, int offset) {

        String countHql = getCount(sql);
        sql = initSort(sql, sort, desc);

        SQLQuery countQuery = getSession().createSQLQuery(countHql);
        SQLQuery query = getSession().createSQLQuery(sql);

        query.setMaxResults(pageSize).setFirstResult(offset);

        setParameter(countQuery, args);
        setParameter(query, args);

        if (hasEntity) {
            query.addEntity(clz);
        } else {
            query.setResultTransformer(Transformers.aliasToBean(clz));
        }

        Pagers<T> pagers = new Pagers<T>();
        pagers.setPageSize(pageSize);
        pagers.setOffSet(offset);
        pagers.setTotal(Integer.parseInt(countQuery.uniqueResult().toString()));
        List<T> lists = query.list();
        if (lists == null) {
            lists = new ArrayList<>();
        }
        pagers.setDatas(lists);
        return pagers;
    }

    @Override
    public Pagers<T> findBySql(String sql, Object[] args, Class<?> clz, boolean hasEntity, int pageSize, int offset) {
        return findBySql(sql, args, clz, hasEntity, null, false, pageSize, offset);

    }

    @Override
    public Pagers<T> findBySql(String sql, Class<?> clz, boolean hasEntity, int pageSize, int offset) {
        return findBySql(sql, null, clz, hasEntity, pageSize, offset);
    }

    @Override
    public Object queryObjectBySql(String sql, Object[] args) {
        Query query = getSession().createQuery(sql);
        setParameter(query, args);
        return query.uniqueResult();
    }

    @Override
    public Object queryObjectBySql(String sql) {
        return queryObjectBySql(sql, null);
    }

    protected String initSort(String hql, String sort, boolean desc) {
        if (StringUtils.isNotBlank(sort)) {
            hql += " order by " + sort;
        }

        if (desc) {
            hql += " desc ";
        }

        return hql;
    }

    protected void setParameter(Query query, Object[] args) {
        if (args != null && args.length > 0) {
            int index = 0;
            for (Object arg : args) {
                query.setParameter(index++, arg);
            }
        }
    }


    @Override
    public void executeSql(String sql) {
        SQLQuery query = getSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void executeSql(String sql, Object[] args) {
        SQLQuery query = getSession().createSQLQuery(sql);
        setParameter(query, args);
        query.executeUpdate();
    }

    private void setPagers(Query query, int pageSize, int offSet) {
        if (pageSize == 0) pageSize = 15;
        query.setFirstResult(offSet).setMaxResults(pageSize);
    }

    private String getCount(String hql) {
        String e = hql.substring(hql.indexOf("from"));
        String c = "select count(1) " + e;
        c = c.replace("fetch", "");
        return c;
    }

}
