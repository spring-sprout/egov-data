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
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Keesun Baik
 */
public class HibernatePersistableEntityInformation<T extends Persistable<ID>, ID extends Serializable> extends
        HibernateMetamodelEntityInformation<T, ID> {

    public HibernatePersistableEntityInformation(Class<T> domainClass, Map<String, ClassMetadata> metadata) {
        super(domainClass, metadata);
    }

    @Override
    public boolean isNew(T entity) {
        return entity.isNew();
    }

    @Override
    public ID getId(T entity) {
        return entity.getId();
    }
}
