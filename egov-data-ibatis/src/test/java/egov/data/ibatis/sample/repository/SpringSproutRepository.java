package egov.data.ibatis.sample.repository;

import java.util.List;

import egov.data.ibatis.repository.SqlMapRepository;
import egov.data.ibatis.sample.domain.SpringSprout;

/**
 * @author Yongkwon Park
 */
public interface SpringSproutRepository extends SqlMapRepository<SpringSprout, Long> {
	
	public List<SpringSprout> findAll();
	
	public SpringSprout findByName(String name);
	
	public SpringSprout insert(SpringSprout springSprout);
	
	public int update(SpringSprout springSprout);
	
	public int delete(Long id);
	
}
