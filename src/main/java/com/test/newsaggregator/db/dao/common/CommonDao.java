package com.test.newsaggregator.db.dao.common;

import com.test.newsaggregator.db.entity.common.AbstractEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public abstract class CommonDao<T extends AbstractEntity> {
    @Autowired
    private SessionFactory _sessionFactory;

    protected Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    @Transactional
    public T save(T entity){
        getSession().save(entity);
        return entity;
    }

    @Transactional
    public T saveOrUpdate(T entity){
        getSession().saveOrUpdate(entity);
        return entity;
    }

    @Transactional
    protected T getById(Class<T> clazz, Long id){
        return (T) getSession().createQuery("from "  + clazz.getSimpleName() + " e where e.id = :id").setParameter("id", id).getSingleResult();
    }

    @Transactional
    protected List<T> get(Class<T> clazz){
        return getSession().createQuery("from " + clazz.getSimpleName()).list();
    }

    @Transactional
    public List<T> getByField(Class<T> clazz, String field, Object value){
        return getSession().createQuery("from "  + clazz.getSimpleName() + " e where e." + field + " = :value").setParameter("value", value).getResultList();
    }

    @Transactional
    public T getByFieldSingle(Class<T> clazz, String field, Object value){
        List<T> list = getSession().createQuery("from "  + clazz.getSimpleName() + " e where e." + field + " = :value").setParameter("value", value).getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
