package egovframework.data.hibernate.repository.support;

import org.hibernate.metadata.ClassMetadata;
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

    private final IdMetadata<T> idMetadata;

    public HibernateMetamodelEntityInformation(Class<T> domainClass, Map<String, ClassMetadata> metadata) {
        super(domainClass);

        Assert.notNull(metadata);
        ClassMetadata classMetadata = metadata.get(domainClass.getName());

        if (!(classMetadata instanceof IdentifiableType)) {
            throw new IllegalArgumentException("The given domain class does not contain an id attribute!");
        }

        this.idMetadata = new IdMetadata<T>((IdentifiableType<T>) classMetadata);
    }

    @Override
    public ID getId(T entity) {
        BeanWrapper entityWrapper = new DirectFieldAccessFallbackBeanWrapper(entity);

        if (idMetadata.hasSimpleId()) {
            return (ID) entityWrapper.getPropertyValue(idMetadata.getSimpleIdAttribute().getName());
        }

        BeanWrapper idWrapper = new DirectFieldAccessFallbackBeanWrapper(idMetadata.getType());
        boolean partialIdValueFound = false;

        for (SingularAttribute<? super T, ?> attribute : idMetadata) {
            Object propertyValue = entityWrapper.getPropertyValue(attribute.getName());

            if (propertyValue != null) {
                partialIdValueFound = true;
            }

            idWrapper.setPropertyValue(attribute.getName(), propertyValue);
        }

        return (ID) (partialIdValueFound ? idWrapper.getWrappedInstance() : null);
    }

    @Override
    public Class<ID> getIdType() {
        return (Class<ID>) idMetadata.getType();
    }

    @Override
    public SingularAttribute<? super T, ?> getIdAttribute() {
        return idMetadata.getSimpleIdAttribute();
    }

    /**
     * Simple value object to encapsulate id specific metadata.
     *
     * @author Oliver Gierke
     */
    private static class IdMetadata<T> implements Iterable<SingularAttribute<? super T, ?>> {

        private final IdentifiableType<T> type;
        private final Set<SingularAttribute<? super T, ?>> attributes;

        @SuppressWarnings("unchecked")
        public IdMetadata(IdentifiableType<T> source) {

            this.type = source;
            this.attributes = (Set<SingularAttribute<? super T, ?>>) (source.hasSingleIdAttribute() ? Collections
                    .singleton(source.getId(source.getIdType().getJavaType())) : source.getIdClassAttributes());
        }

        public boolean hasSimpleId() {
            return attributes.size() == 1;
        }

        public Class<?> getType() {

            try {
                return type.getIdType().getJavaType();
            } catch (IllegalStateException e) {
                // see https://hibernate.onjira.com/browse/HHH-6951
                IdClass annotation = type.getJavaType().getAnnotation(IdClass.class);
                return annotation == null ? null : annotation.value();
            }
        }

        public SingularAttribute<? super T, ?> getSimpleIdAttribute() {
            return attributes.iterator().next();
        }

        /*
           * (non-Javadoc)
           * @see java.lang.Iterable#iterator()
           */
        public Iterator<SingularAttribute<? super T, ?>> iterator() {
            return attributes.iterator();
        }
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
