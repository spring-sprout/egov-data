package egov.data.jpa.repository.support;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import egov.data.jpa.repository.EntityRepository;

public class EntityRepositoryFactory <T, I extends Serializable> extends JpaRepositoryFactory {

	private EntityManager entityManager;

	public EntityRepositoryFactory(EntityManager entityManager) {
		super(entityManager);
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	protected Object getTargetRepository(RepositoryMetadata metadata) {
		return new EntityRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
	}

	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return EntityRepository.class;
	}
}
