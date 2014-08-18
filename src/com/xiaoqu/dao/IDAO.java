package com.xiaoqu.dao;

import java.util.List;  

/** 
 *  
 * @author tianyu 
 * @description  
 * @param <T> 
 */  
public interface IDAO<T> {  
    T get(int id);  
    T add(T obj);  
    boolean delete(T obj);  
    T update(T obj) throws Exception;  
    List<T> query(String hql,Object... params);  
    int count();  
    int count(String hql,Object...params);  
    List<T> query(String hql,int page,int pagesize,Object... params);  
}  