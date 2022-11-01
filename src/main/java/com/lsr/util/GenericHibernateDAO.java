package com.lsr.util;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;



/**
 *
 * @author hendra
 */
public class GenericHibernateDAO <T, K extends Serializable> implements GenericDAO<T, K> {
   
    private Class <T> persistentClass;
	
    @PersistenceContext
    private EntityManager em;
	
    public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@SuppressWarnings("unchecked")
	public GenericHibernateDAO() {
        persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public T findById(K id)
	{
		return  getCurrentSession().find(getPersistentClass(), id);
	}
	
	public T getById(K id)
	{
		return  getCurrentSession().find(getPersistentClass(), id);
	}
    
    protected EntityManager getCurrentSession()
	{
		return em;
	}
    
    
    @Override
    public void save(T newEntity) {
    	
       getCurrentSession().persist(newEntity);
       
    }
    

    @Override
    public void flush() {
        getCurrentSession().flush();
    }

    @Override
    public T update(T editedEntity) {
    	
       return  getCurrentSession().merge(editedEntity);
       
    }

    @Override
    public void delete(K id) {
    	Object entity = getCurrentSession().find(getPersistentClass(), id);
        
        getCurrentSession().remove(getCurrentSession().merge(entity));
       
    }
    
    @Override
	public void delete(T deletedEntity) {
        
        getCurrentSession().remove(getCurrentSession().merge(deletedEntity));
		
	}

    @Override
    public void clear() {
        getCurrentSession().clear();
    }
 
}
