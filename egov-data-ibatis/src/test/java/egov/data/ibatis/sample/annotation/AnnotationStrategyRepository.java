package egov.data.ibatis.sample.annotation;

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
@Namespace("AnnotationStrategy")
public interface AnnotationStrategyRepository extends SqlMapRepository<SpringSprout, Long> {
	
	@Statement("findAll")
	public List<SpringSprout> selectAll();
	
	@Statement("findByName")
	public SpringSprout selectByName(String name);
	
	@Statement("insert")
	public SpringSprout save(SpringSprout springSprout);
	
	@Statement("insertMap")
	public void saveMap(Map<String, String> map);
	
	@Statement("insertVars")
	public void saveVars(@Param("name") String name, @Param("age") Integer age);

	@Statement("update")
	public int newOne(SpringSprout springSprout);
	
	@Statement("delete")
	public int remove(Long id);
	
	@Transactional
	@Rollback(false)
	public void saveAndFlush(SpringSprout springSprout);

	@Transactional(readOnly=false)
	public void findOne(Long id);
	
}
