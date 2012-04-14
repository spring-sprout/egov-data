package egov.data.ibatis.repository.query;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.QueryMethod.Type;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.mapping.statement.StatementType;

public class SqlMapQueryTest {
	
	String namedQueryName = "namedQueryName";

	QueryMethod queryMethod;
	SqlMapExecutorDelegate delegate;
	MappedStatement mappedStatement;
	SqlMapClientTemplate template;
	
	@Before
	public void setup() throws SecurityException, NoSuchMethodException {
		this.queryMethod = mock(QueryMethod.class);
		this.delegate = mock(SqlMapExecutorDelegate.class);
		this.mappedStatement = mock(MappedStatement.class);
		this.template = mock(SqlMapClientTemplate.class);
		
		when(queryMethod.getNamedQueryName()).thenReturn(namedQueryName);
		when(delegate.getMappedStatement(namedQueryName)).thenReturn(mappedStatement);
	}
	
	
	@Test
	public void COLLECTION을_반환하는_조회쿼리_실행() {
		when(mappedStatement.getStatementType()).thenReturn(StatementType.SELECT);
		when(queryMethod.getType()).thenReturn(Type.COLLECTION);
		
		new SqlMapQuery(queryMethod, delegate, template).execute(null);
		verify(template).queryForList(namedQueryName, null);
	}
	
	@Test
	public void SINGLE_ENTITY를_반환하는_조회쿼리_실행() {
		when(mappedStatement.getStatementType()).thenReturn(StatementType.SELECT);
		when(queryMethod.getType()).thenReturn(Type.SINGLE_ENTITY);
		
		new SqlMapQuery(queryMethod, delegate, template).execute(null);
		Mockito.verify(template).queryForObject(namedQueryName, null);
	}
	
	@Test
	public void 등록쿼리_실행() {
		when(mappedStatement.getStatementType()).thenReturn(StatementType.INSERT);
		
		new SqlMapQuery(queryMethod, delegate, template).execute(null);
		verify(template).insert(namedQueryName, null);
	}
	
	@Test
	public void 수정쿼리_실행() {
		when(mappedStatement.getStatementType()).thenReturn(StatementType.UPDATE);
		
		new SqlMapQuery(queryMethod, delegate, template).execute(null);
		verify(template).update(namedQueryName, null);
	}
	
	@Test
	public void 삭제쿼리_실행() {
		when(mappedStatement.getStatementType()).thenReturn(StatementType.DELETE);
		
		new SqlMapQuery(queryMethod, delegate, template).execute(null);
		verify(template).delete(namedQueryName, null);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void 알수없는_쿼리_유형을_만나면_예외발생() {
		new SqlMapQuery(queryMethod, delegate, template).execute(null);
	}
	
	@Test
	public void 파라메터가_없이_쿼리_실행() {
		when(mappedStatement.getStatementType()).thenReturn(StatementType.INSERT);
		
		new SqlMapQuery(queryMethod, delegate, template).execute(null);
		verify(template).insert(namedQueryName, null);
	}
	
	@Test
	public void 하나의_파라메터로_쿼리_실행() {
		when(mappedStatement.getStatementType()).thenReturn(StatementType.INSERT);
		
		Object[] parameters = new Object[]{"Condition Value"};
		new SqlMapQuery(queryMethod, delegate, template).execute(parameters);
		verify(template).insert(namedQueryName, parameters[0]);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 하나_이상의_파라메터로_쿼리_실행하면_예외발생() {
		when(mappedStatement.getStatementType()).thenReturn(StatementType.INSERT);
		
		Object[] parameters = new Object[]{"Condition Value", "Condition Value"};
		new SqlMapQuery(queryMethod, delegate, template).execute(parameters);
	}
	
}
