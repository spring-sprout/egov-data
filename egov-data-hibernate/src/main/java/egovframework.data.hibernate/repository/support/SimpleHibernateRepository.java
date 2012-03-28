/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.data.hibernate.repository.support;

import com.sun.source.tree.AssertTree;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.SimpleDerivation;
import egovframework.data.hibernate.repository.HibernateRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Keesun Baik
 */
@Repository
@Transactional(readOnly = true)
public class SimpleHibernateRepository<T, ID extends Serializable> implements HibernateRepository<T, ID> {

    private final HibernateEntityInformation<T, ?> entityInformation;
    private final SessionFactory sessionFactory;

    public SimpleHibernateRepository(HibernateEntityInformation<T, ?> entityInformation, SessionFactory sessionFactory) {
        Assert.notNull(entityInformation);
        Assert.notNull(sessionFactory);
        
        this.entityInformation = entityInformation;
        this.sessionFactory = sessionFactory;
    }
    
    public SimpleHibernateRepository(Class<T> domainClass, SessionFactory sessionFactory) {
        this(HibernateEntityInformationSupport.getMetadata(domainClass, sessionFactory), sessionFactory);
    }

    @Transactional
    public void delete(ID id) {
        Assert.notNull(id, "The given id must not be null!");
        delete(findOne(id));
    }

    @Transactional
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        getSession().delete(entity);
    }

    @Transactional
    public void delete(Iterable<? extends T> entities) {
        Assert.notNull(entities, "The given Iterable of entities must not be null!");

        for(T entity : entities) {
            delete(entity);
        }
    }

    @Transactional
    public void deleteInBatch(Iterable<T> entities) {
        throw new UnsupportedOperationException();
    }

    @Transactional
    public void deleteAll() {
        for(T element : findAll()) {
            delete(element);
        }
    }

    public T findOne(ID id) {
        Assert.notNull(id, "The given id must not be null");

        return (T) getSession().get(getDomainClass(), id);
    }

    @Override
    public boolean exists(ID id) {
        Assert.notNull(id, "The given id must not be null");

        return findOne(id) != null;
    }

    @Override
    public List<T> findAll() {
        return getCriteria(null, null).list();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> findAll(Sort sort) {
        return getCriteria(sort, null).list();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        if (pageable == null) {
            return new PageImpl<T>(findAll());
        }

        return (Page<T>) getCriteria(null, pageable).list();
    }

    @Transactional
    public T save(T entity) {
        if(entityInformation.isNew(entity)) {
            getSession().save(entity);
            return entity;
        } else {
            getSession().update(entity);
            return entity;
        }
    }

    @Transactional
    public List<T> save(Iterable<? extends T> entities) {
        List<T> result = new ArrayList<T>();
        
        if(entities == null) {
            return result;
        }
        
        for(T entity : entities) {
            result.add(save(entity));
        }

        return result;
    }

    @Transactional
    public void flush() {
        getSession().flush();
    }

    @Transactional
    public T saveAndFlush(T entity) {
        T result = save(entity);
        flush();
        return result;
    }

    private Class getDomainClass() {
        return entityInformation.getJavaType();
    }

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    protected Criteria getCriteria(Sort sortParam, Pageable pageable) {
        Criteria criteria = getSession().createCriteria(getDomainClass());

        Sort sort = pageable == null ? sortParam : pageable.getSort();

        if(sort != null) {
            for(Sort.Order order : sort) {
                if(order.getDirection().equals(Sort.Direction.ASC)) {
                    criteria.addOrder(Order.asc(order.getProperty()));
                } else if(order.getDirection().equals(Sort.Direction.DESC)) {
                    criteria.addOrder(Order.desc(order.getProperty()));
                }
            }
        }

        if(pageable != null) {
            criteria.setFirstResult(pageable.getOffset());
            criteria.setMaxResults(pageable.getPageSize());
        }

        return criteria;
    }

}
