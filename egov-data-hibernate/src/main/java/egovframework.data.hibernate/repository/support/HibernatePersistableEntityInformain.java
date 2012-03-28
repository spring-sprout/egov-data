package egovframework.data.hibernate.repository.support;

import org.hibernate.metadata.ClassMetadata;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Keesun Baik
 */
public class HibernatePersistableEntityInformain<T extends Persistable<ID>, ID extends Serializable> extends
        HibernateMetamodelEntityInformation<T, ID> {

    public HibernatePersistableEntityInformain(Class<T> domainClass, Map<String, ClassMetadata> metadata) {
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
