package com.xiaoqu.dao;

import java.lang.reflect.ParameterizedType;  
import java.sql.SQLException;  
import java.util.List;  
  
import org.hibernate.FlushMode;  
import org.hibernate.HibernateException;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.annotations.FlushModeType;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.orm.hibernate3.HibernateAccessor;  
import org.springframework.orm.hibernate3.HibernateCallback;  
import org.springframework.orm.hibernate3.HibernateTemplate;  
  
  
public abstract class DAOImpl<T> implements IDAO<T> {  
  
    protected HibernateTemplate hibernateTemplate;  
      
    @Autowired  
    public void setSessionFactory(SessionFactory sessionFactory) {  
        hibernateTemplate = new HibernateTemplate(sessionFactory);  
        //hibernateTemplate.setFlushMode(HibernateAccessor.FLUSH_AUTO);  
    }  
      
    public T add(T obj) {  
        hibernateTemplate.save(obj);  
        return obj;  
    }  
  
    public boolean delete(T obj) {  
        try{  
            hibernateTemplate.delete(obj);  
            return true;  
        }catch (Exception e) {  
            return false;  
        }  
    }  
  
    @SuppressWarnings("unchecked")  
    public T get(int id) {  
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
        return hibernateTemplate.get(clazz, id);  
    }  
  
    public T update(T obj) throws Exception {  
        hibernateTemplate.update(obj);  
        return obj;  
    }  
  
    @SuppressWarnings("unchecked")  
    @Override  
    public List<T> query(String hql, Object... params) {  
        return (List<T>) hibernateTemplate.find(hql, params);  
    }  
    @SuppressWarnings("unchecked")  
    @Override  
    public int count() {          
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];  
        String hql = "select count(*) from " + clazz.getName();  
        return count(hql);  
    }  
    @Override  
    @SuppressWarnings("rawtypes")  
    public int count(String hql,Object...params) {        
        List result = hibernateTemplate.find(hql,params);         
        return ((Long) result.get(0)).intValue();  
    }  
  
    @Override  
    public List<T> query(final String hql, final int page, final int pagesize, final Object... params) {  
        @SuppressWarnings("unchecked")  
        List<T> result = hibernateTemplate.executeFind(new HibernateCallback<List<T>>() {  
            @Override  
            public List<T> doInHibernate(Session session) throws HibernateException,  SQLException {  
                Query q = session.createQuery(hql);  
                q.setFirstResult(page*pagesize-pagesize);  
                q.setMaxResults(pagesize);  
                int i=0;  
                for (Object o : params) {                 
                    q.setParameter(i, o);  
                    i++;  
                }  
                return q.list();  
            }  
        });  
        return result;  
    }  
      
      
      
  
}  
