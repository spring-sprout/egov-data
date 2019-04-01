package egov.data.jpa.repository.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import egov.data.jpa.repository.QueryMethod;

public class QueryMethodUtils {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public static String quessQueryMethodName(Object bean){
		
		Class<?> beanClass = bean.getClass();
		String queryMethodName = ClassUtils.getShortNameAsProperty(beanClass);
		QueryMethod entityQuery = AnnotationUtils.findAnnotation(beanClass, QueryMethod.class);

		if(entityQuery != null){
			queryMethodName = StringUtils.hasText(entityQuery.value())  ? entityQuery.value() : queryMethodName;
		}

		Assert.isTrue(queryMethodName.toLowerCase().startsWith("findby"), "queryMethod must start with 'findBy~'");
		return queryMethodName;
	}
}
