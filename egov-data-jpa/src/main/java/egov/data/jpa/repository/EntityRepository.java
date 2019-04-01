package egov.data.jpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface EntityRepository<T, ID extends Serializable> extends 
	JpaRepository<T, ID>, JpaSpecificationExecutor<T>, QueryMethodExecutor<T> , NativeQueryExecutor{

}
