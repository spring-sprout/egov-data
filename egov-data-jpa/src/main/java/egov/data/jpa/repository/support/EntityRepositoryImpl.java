package egov.data.jpa.repository.support;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import egov.data.jpa.repository.EntityRepository;

public class EntityRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements EntityRepository<T,ID>{

	private final Class<T> domainClass;
	private final EntityManager em;
	
	public EntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
		super(entityInformation, em);
		this.domainClass = entityInformation.getJavaType();
		this.em = em;
	}
	public EntityRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.domainClass = domainClass;
		this.em = em;
	}
	
	public List<T> findAll(Object bean) {
		return findAll(QueryMethodUtils.quessQueryMethodName(bean), bean);
	}
	public List<T> findAll(Object bean, Sort sort) {
		return findAll(QueryMethodUtils.quessQueryMethodName(bean), bean, sort);
	}
	public Page<T> findAll(Object bean, Pageable pageable) {
		return findAll(QueryMethodUtils.quessQueryMethodName(bean), bean, pageable);
	}
	public List<T> findAll(String queryMethodName, Object param) {
		return super.findAll(new QueryMethodSpecification<T>(domainClass, queryMethodName, param));
	}
	public List<T> findAll(String queryMethodName, Object param, Sort sort) {
		return super.findAll(new QueryMethodSpecification<T>(domainClass, queryMethodName, param), sort);
	}
	public Page<T> findAll(String queryMethodName, Object param, Pageable pageable) {
		return super.findAll(new QueryMethodSpecification<T>(domainClass, queryMethodName, param), pageable);
	}
	
	public List<?> executeQuery(String sqlString) {
		return em.createNativeQuery(sqlString).getResultList();
	}
	public List<?> executeQuery(String sqlString, Object param) {
		return setParameters(em.createNativeQuery(sqlString), param).getResultList();
	}
	public int executeUpdate(String sqlString) {
		return em.createNativeQuery(sqlString).executeUpdate();
	}
	public int executeUpdate(String sqlString, Object param) {
		return setParameters(em.createNativeQuery(sqlString), param).executeUpdate();
	}
	
	private Query setParameters(Query query, Object param){
		BeanWrapper paramWrapper = PropertyAccessorFactory.forBeanPropertyAccess(param);
		Set<Parameter<?>> parameters = query.getParameters();
		for(Parameter<?> parameter : parameters){
			String name = parameter.getName();
			Object value = paramWrapper.getPropertyValue(name);		
			query.setParameter(name, value);
		}
		return query;
	}
	//ClasspathScanningPersistenceUnitPostProcessor h;
}
