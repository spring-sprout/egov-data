package egov.data.ibatis.sample.namespace;

import java.util.*;

import org.springframework.test.annotation.*;
import org.springframework.transaction.annotation.*;

import egov.data.ibatis.repository.*;
import egov.data.ibatis.repository.annotation.*;
import egov.data.ibatis.sample.domain.*;

/**
 * @author Yongkwon Park
 * @author Yunseok Choi
 */
@Namespace("NamespaceStrategy")
public interface NamespaceStrategyRepository extends SqlMapRepository<SpringSprout, Long> {
	
	public List<SpringSprout> findAll();
	
	public SpringSprout findByName(String name);
	
	public SpringSprout insert(SpringSprout springSprout);
	
	public void insertMap(Map<String, String> map);
	
	public int update(SpringSprout springSprout);
	
	public int delete(Long id);
	
	@Transactional
	@Rollback(false)
	public void saveAndFlush(SpringSprout springSprout);

	@Transactional(readOnly=false)
	public void findOne(Long id);
	
}
