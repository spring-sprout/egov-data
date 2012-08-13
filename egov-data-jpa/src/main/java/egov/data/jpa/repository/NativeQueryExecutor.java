package egov.data.jpa.repository;

import java.util.List;

public interface NativeQueryExecutor {

	public List<?> executeQuery(String sqlString);
	public List<?> executeQuery(String sqlString, Object param);

	public int executeUpdate(String sqlString);
	public int executeUpdate(String sqlString, Object param);
	
}
