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

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.data.domain.Persistable;
import org.springframework.data.repository.core.support.AbstractEntityInformation;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Map;

/**
 * @author Keesun Baik
 */
public abstract class HibernateEntityInformationSupport<T, ID extends Serializable> extends AbstractEntityInformation<T, ID> 
        implements HibernateEntityInformation<T, ID> {


    public HibernateEntityInformationSupport(Class<T> domainClass) {
        super(domainClass);
    }
    
    public static <T> HibernateEntityInformation<T, ?> getMetadata(Class<T> domainClass, SessionFactory sessionFactory) {
        Assert.notNull(domainClass);
        Assert.notNull(sessionFactory);
        
        Map<String, ClassMetadata> metadata = sessionFactory.getAllClassMetadata();

        if(Persistable.class.isAssignableFrom(domainClass)) {
            return new HibernatePersistableEntityInformation(domainClass, metadata);
        } else {
            return new HibernateMetamodelEntityInformation(domainClass, metadata);
        }
    }

    public String getEntityName() {
        Class<?> domainClass = getJavaType();
        Entity entity = domainClass.getAnnotation(Entity.class);
        boolean hasName = null != entity && StringUtils.hasText(entity.name());

        return hasName ? entity.name() : domainClass.getSimpleName();
    }

}
