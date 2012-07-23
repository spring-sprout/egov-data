package egov.data.hibernate.sample.repository;

import egov.data.hibernate.repository.HibernateRepository;
import egov.data.hibernate.sample.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;

/**
 * @author Keesun Baik
 */
public interface UserRepository extends HibernateRepository<User, Integer> {


}
