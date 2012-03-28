package egovframework.data.hibernate.repository.support;

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
            return new HibernatePersistableEntityInformain(domainClass, metadata);
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
