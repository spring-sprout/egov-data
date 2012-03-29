package egov.data.hibernate.repository.support;

import egov.data.hibernate.repository.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author Keesun Baik
 */
public class HibernateRepositoryFactory extends RepositoryFactorySupport {

	private final SessionFactory sessionFactory;

	/**
	 * Creates a new {@link HibernateRepositoryFactory}.
	 *
	 * @param sessionFactory must not be {@literal null}
	 */
	public HibernateRepositoryFactory(SessionFactory sessionFactory) {
		Assert.notNull(sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.data.repository.support.RepositoryFactorySupport#
	 * getTargetRepository(java.lang.Class)
	 */
	@Override
	protected Object getTargetRepository(RepositoryMetadata metadata) {
		return getTargetRepository(metadata, sessionFactory);
	}

	/**
	 * Callback to create a {@link org.springframework.data.jpa.repository.JpaRepository} instance with the given {@link EntityManager}
	 *
	 * @param <T>
	 * @param <ID>
	 * @param sessionFactory
	 * @see #getTargetRepository(RepositoryMetadata)
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T, ID extends Serializable> HibernateRepository<?, ?> getTargetRepository(RepositoryMetadata metadata,
	                                                                               SessionFactory sessionFactory) {

		HibernateEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainClass());
		return new SimpleHibernateRepository(entityInformation, sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.data.repository.support.RepositoryFactorySupport#
	 * getRepositoryBaseClass()
	 */
	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

		if (isQueryDslExecutor(metadata.getRepositoryInterface())) {
			// TODO
			throw new UnsupportedOperationException();
		} else {
			return SimpleHibernateRepository.class;
		}
	}

	/**
	 * Returns whether the given repository interface requires a QueryDsl specific implementation to be chosen.
	 *
	 * @param repositoryInterface
	 * @return
	 */
	private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
		// TODO
//		return QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.data.repository.support.RepositoryFactorySupport#
	 * getQueryLookupStrategy
	 * (org.springframework.data.repository.query.QueryLookupStrategy.Key)
	 */
	@Override
	protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key) {
		// TODO
//		return JpaQueryLookupStrategy.create(entityManager, key, extractor);
		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.data.repository.support.RepositoryFactorySupport#
	 * getEntityInformation(java.lang.Class)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T, ID extends Serializable> HibernateEntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {

		return (HibernateEntityInformation<T, ID>) HibernateEntityInformationSupport.getMetadata(domainClass, sessionFactory);
	}

}
