package tw.com.SF.bowlingWeb.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T> {
	protected Logger logger = Logger.getLogger(getClass());
	protected Class<T> entityClass;
	
	@Autowired protected SessionFactory sessionFactory;

	private ParameterizedType findParameterizedType(Class<?> clazz) {
		Type genericSuperclass = clazz.getGenericSuperclass();
		if (!(genericSuperclass instanceof ParameterizedType)) {
			return findParameterizedType(clazz.getSuperclass());
		}
		return (ParameterizedType) genericSuperclass;
	}

	public AbstractDAO() {
		this.entityClass = (Class<T>) findParameterizedType(getClass()).getActualTypeArguments()[0];
	}
	
	private Criteria createCriteriaByExample(Object example) {
		Class<?> cls = example.getClass();
		Criteria c = sessionFactory.getCurrentSession().createCriteria(cls);
		c.add(Example.create(example));
		return c;
	}
	
	public List<T> getByExample(T example, String... orders) throws Exception {
		Criteria criteria = createCriteriaByExample(example);
		if(orders!=null) {
			String[] tmp;
			for(String order:orders) {
				tmp = order.split(" ");
				switch (tmp.length) {
				case 0:
					break;
				case 1:
					criteria.addOrder(Order.asc(tmp[0]));
					break;
				default:
					if("desc".equals(tmp[tmp.length-1])) {
						criteria.addOrder(Order.desc(tmp[0]));
					} else {
						criteria.addOrder(Order.asc(tmp[0]));
					}
					break;
				}
			}
		}
		return criteria.list();
	}
	
	public List<T> get() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(entityClass).list();
	}
	
	public Criteria createCriteria() throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(entityClass);
	}
	
	public Criteria createCriteria(Class<?> clazz) throws Exception {
		return sessionFactory.getCurrentSession().createCriteria(clazz);
	}
	
	public T get(Serializable id) throws Exception {
		if(id==null) return null;
		return (T)sessionFactory.getCurrentSession().get(entityClass, id);
	}
	public void insertOrUpdate(T t) throws Exception {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}
	public void insert(T t) throws Exception {
		sessionFactory.getCurrentSession().persist(t);
	}
	public void update(T t) throws Exception {
		sessionFactory.getCurrentSession().update(t);
	}
	public void insertOrUpdateAll(Collection<T> objs) throws Exception {
		Session session = sessionFactory.getCurrentSession();
		int counter = 0;
		for(T obj:objs) {
			session.saveOrUpdate(obj);
			++counter;
			if(counter%100==0) session.flush();
		}
	}
	public void deleteAll(Collection<T> objs) throws Exception {
		if(objs==null || objs.size()==0) return;
		
		Session session = sessionFactory.getCurrentSession();
		int counter = 0;
		for(T obj:objs) {
			session.delete(obj);
			++counter;
			if(counter%100==0) session.flush();
		}
	}
	
	public void delete(T t) throws Exception {
		if(t!=null) {
			sessionFactory.getCurrentSession().delete(t);
		}
	}
	
	/**Â∞áÊü•Ë©¢Á?êÊ?úË?âÊ?õÊ?êMAP
	 * keyÔºöidField
	 * */
	public Map<Object,T> queryToMap(Class<T> clazz, String idField) throws Exception {
		Map<Object,T> result = new LinkedHashMap<Object, T>();
		Criteria criteria = createCriteria(clazz);
		criteria.addOrder(Order.asc(idField));
		List<T> list = criteria.list();
		for(T t : list) {
			Object o = BeanUtils.getProperty(t, idField);
			if(o != null){
				result.put(o, t);
			}
		}
		return result;
	}
	
}
