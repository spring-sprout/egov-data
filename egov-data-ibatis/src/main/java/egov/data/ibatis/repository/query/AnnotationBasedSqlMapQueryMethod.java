/*
 * Copyright 2008-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egov.data.ibatis.repository.query;

import java.lang.annotation.*;
import java.lang.reflect.*;

import org.springframework.data.repository.core.*;
import org.springframework.data.repository.query.*;

import egov.data.ibatis.repository.annotation.*;
import egov.data.ibatis.repository.annotation.Param;

/**
 * Repository 인터페이스에 있는 {@link Namespace} 및 쿼리메소드의 {@link Statement} 처리 전략 클래스
 * 
 * @author Yunseok Choi
 *
 */
public class AnnotationBasedSqlMapQueryMethod extends QueryMethod {
	
	private Class<?> repositoryInterface;
	private Method method;
	private String[] parameterNames;
	
	public AnnotationBasedSqlMapQueryMethod(Method method, RepositoryMetadata metadata) {
		super(method, metadata);
		
		this.repositoryInterface = metadata.getRepositoryInterface();
		this.method = method;
	}
	
	@Override
	public String getNamedQueryName() {
		if (repositoryInterface.isAnnotationPresent(Namespace.class)) {
			Namespace namespace = repositoryInterface.getAnnotation(Namespace.class);
			return String.format("%s.%s", namespace.value(), getName());
		}
		
		return super.getNamedQueryName();
	}
	
	@Override
	public String getName() {
		if (method.isAnnotationPresent(Statement.class))
			return method.getAnnotation(Statement.class).value();
		
		return super.getName();
	}
	
	public String[] getParameterNames() {
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		parameterNames = new String[parameterAnnotations.length];
		
		for (int i = 0; i < parameterAnnotations.length; i++) {
			Annotation[] annotations = parameterAnnotations[i];
			for (int j = 0; j < annotations.length; j++) {
				Annotation annotation = annotations[j];
				if (annotation.annotationType().isAssignableFrom(Param.class)) {
					Param param = (Param) annotation;
					parameterNames[i] = param.value();
				}
			}
		}
		
		return parameterNames;
	}

}
