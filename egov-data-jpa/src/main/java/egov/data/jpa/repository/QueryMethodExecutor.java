package egov.data.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface QueryMethodExecutor<T> {

	public List<T> findAll(Object param);
	public List<T> findAll(Object param, Sort sort);
	public Page<T> findAll(Object param, Pageable pageable);
	
	public List<T> findAll(String queryMethodName, Object param);
	public List<T> findAll(String queryMethodName, Object param, Sort sort);
	public Page<T> findAll(String queryMethodName, Object param, Pageable pageable);
	
}
