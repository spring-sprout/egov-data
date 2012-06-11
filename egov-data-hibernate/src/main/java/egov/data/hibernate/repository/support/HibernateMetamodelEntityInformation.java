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
package egov.data.hibernate.repository.support;

import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.IdentifierType;
import org.hibernate.type.Type;
import org.springframework.beans.*;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import javax.persistence.IdClass;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Keesun Baik
 */
public class HibernateMetamodelEntityInformation<T, ID extends Serializable> extends HibernateEntityInformationSupport<T, ID>
        implements HibernateEntityInformation<T, ID> {

    private final Type idType;
	private final String idProperty;

    public HibernateMetamodelEntityInformation(Class<T> domainClass, ClassMetadata metadata) {
        super(domainClass);

        Assert.notNull(metadata);
	    
        Type idType = metadata.getIdentifierType();
        if (idType == null) {
            throw new IllegalArgumentException("The given domain class does not contain an id attribute!");
        }
	    
	    String idProperty = metadata.getIdentifierPropertyName();

        this.idType = idType;
	    this.idProperty = idProperty;
    }

    @Override
    public ID getId(T entity) {
        BeanWrapper entityWrapper = new DirectFieldAccessFallbackBeanWrapper(entity);
		return (ID) entityWrapper.getPropertyValue(idProperty);
    }

    @Override
    public Class<ID> getIdType() {
        return idType.getReturnedClass();
    }

    /**
     * Custom extension of {@link BeanWrapperImpl} that falls back to direct field access in case the object or type being
     * wrapped does not use accessor methods.
     *
     * @author Oliver Gierke
     */
    private static class DirectFieldAccessFallbackBeanWrapper extends BeanWrapperImpl {

        public DirectFieldAccessFallbackBeanWrapper(Object entity) {
            super(entity);
        }

        public DirectFieldAccessFallbackBeanWrapper(Class<?> type) {
            super(type);
        }

        /*
           * (non-Javadoc)
           * @see org.springframework.beans.BeanWrapperImpl#getPropertyValue(java.lang.String)
           */
        @Override
        public Object getPropertyValue(String propertyName) throws BeansException {
            try {
                return super.getPropertyValue(propertyName);
            } catch (NotReadablePropertyException e) {
                Field field = ReflectionUtils.findField(getWrappedClass(), propertyName);
                ReflectionUtils.makeAccessible(field);
                return ReflectionUtils.getField(field, getWrappedInstance());
            }
        }

        /*
           * (non-Javadoc)
           * @see org.springframework.beans.BeanWrapperImpl#setPropertyValue(java.lang.String, java.lang.Object)
           */
        @Override
        public void setPropertyValue(String propertyName, Object value) throws BeansException {
            try {
                super.setPropertyValue(propertyName, value);
            } catch (NotWritablePropertyException e) {
                Field field = ReflectionUtils.findField(getWrappedClass(), propertyName);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, getWrappedInstance(), value);
            }
        }
    }
}
