package egov.data.jpa.repository.support;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.repository.query.parser.Part.Type;
import org.springframework.data.repository.query.parser.PartTree;
import org.springframework.data.repository.query.parser.PartTree.OrPart;

public class QueryMethodSpecification<T> implements Specification<T>{

	protected final Log logger = LogFactory.getLog(getClass());

	private final PartTree partTree;
	private final BeanWrapper paramWrapper;
	
	public QueryMethodSpecification(Class<T> domainClass, String queryMethodName, Object parameter){
		this.partTree = new PartTree(queryMethodName, domainClass);
		this.paramWrapper = PropertyAccessorFactory.forBeanPropertyAccess(parameter);
	}
	
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		query.distinct(partTree.isDistinct());

		Predicate base = null;
		for (OrPart node : partTree) {
			logger.debug(node);
			Predicate criteria = null;
			for (Part part : node) {
				
				Predicate p = toPredicate(part, paramWrapper, root, query, builder);
				if(p != null){
					logger.debug("\t"+part+"==>"+p);
					criteria = criteria == null ? p : builder.and(criteria, p);;
				}else{
					logger.debug("\t"+part);
				}
			}
			base = base == null ? criteria : builder.or(base, criteria);
		}
		return base;
	}

	
	
	@SuppressWarnings("unchecked")
	protected Predicate toPredicate(Part part, BeanWrapper paramWrapper, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		PropertyPath property = part.getProperty();
		String paramName = property.getSegment();
		Type type = part.getType();


		//Expression<Object> path = toExpressionRecursively(root, property);
	
		
		if(Type.BETWEEN.equals(type)){
			Comparable<?> min = (Comparable<?>)paramWrapper.getPropertyValue(paramName+"Min");
			Comparable<?> max = (Comparable<?>)paramWrapper.getPropertyValue(paramName+"Max");

			if(min != null && max != null){
				return builder.between(getComparablePath(root, part), min, max);
			}else if(min != null && max == null){
				return builder.greaterThanOrEqualTo(getComparablePath(root, part), min);
			}else if(min == null && max != null){
				return builder.lessThanOrEqualTo(getComparablePath(root, part), max);
			}
			
		}else if(Type.AFTER.equals(type)){
			
			
		}else if(Type.GREATER_THAN.equals(type)){
			Comparable<?> greaterThan = (Comparable<?>)paramWrapper.getPropertyValue(paramName);
			if(greaterThan != null){
				return builder.greaterThan(getComparablePath(root, part), greaterThan);
			}
		}else if(Type.GREATER_THAN_EQUAL.equals(type)){
			Comparable<?> greaterThanEqual = (Comparable<?>)paramWrapper.getPropertyValue(paramName);
			if(greaterThanEqual != null){
				return builder.greaterThanOrEqualTo(getComparablePath(root, part), greaterThanEqual);
			}
		}else if(Type.BEFORE.equals(type)){
			
			
		}else if(Type.LESS_THAN.equals(type)){
			Comparable<?> lessThan = (Comparable<?>)paramWrapper.getPropertyValue(paramName);
			if(lessThan != null){
				return builder.lessThan(getComparablePath(root, part), lessThan);
			}
		}else if(Type.LESS_THAN_EQUAL.equals(type)){
			Comparable<?> greaterThan = (Comparable<?>)paramWrapper.getPropertyValue(paramName);
			if(greaterThan != null){
				return builder.lessThanOrEqualTo(getComparablePath(root, part), greaterThan);
			}
		}else if(Type.IS_NULL.equals(type)){
			
		}else if(Type.IS_NOT_NULL.equals(type)){
		
		}else if(Type.NOT_IN.equals(type)){
		
		}else if(Type.IN.equals(type)){
		
		}else if(Type.STARTING_WITH.equals(type)){
			Object likeValue = paramWrapper.getPropertyValue(paramName);
			if(likeValue != null){
				Expression<String> likePath = getTypedPath(root, part);
				return builder.like(likePath, "%"+likeValue.toString());
			}
		}else if(Type.ENDING_WITH.equals(type)){
			Object likeValue = paramWrapper.getPropertyValue(paramName);
			if(likeValue != null){
				Expression<String> likePath = getTypedPath(root, part);
				return builder.like(likePath, likeValue.toString()+"%");
			}
		}else if(Type.CONTAINING.equals(type)){
			Object likeValue = paramWrapper.getPropertyValue(paramName);
			if(likeValue != null){
				Expression<String> likePath = getTypedPath(root, part);
				return builder.like(likePath, "%"+likeValue.toString()+"%");
			}
		}else if(Type.LIKE.equals(type)){
			Object likeValue = paramWrapper.getPropertyValue(paramName);
			if(likeValue != null){
				Expression<String> likePath = getTypedPath(root, part);
				return builder.like(likePath, likeValue.toString());
			}
		}else if(Type.NOT_LIKE.equals(type)){
			
		}else if(Type.TRUE.equals(type)){
			Expression<Boolean> truePath = getTypedPath(root, part);
			return builder.isTrue(truePath);

		}else if(Type.FALSE.equals(type)){
			Expression<Boolean> falsePath = getTypedPath(root, part);
			return builder.isFalse(falsePath);
		
		}else if(Type.SIMPLE_PROPERTY.equals(type)){
			Object equalValue = paramWrapper.getPropertyValue(paramName);
			if(equalValue != null){
				Expression<Object> equalPath = getTypedPath(root, part);
				return builder.equal(equalPath , equalValue);
			}
		}else if(Type.NEGATING_SIMPLE_PROPERTY.equals(type)){
			
		}
		
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	private Expression<? extends Comparable> getComparablePath(Root<?> root, Part part) {
		return getTypedPath(root, part);
	}

	private <Y> Expression<Y> getTypedPath(Root<?> root, Part part) {
		return toExpressionRecursively(root, part.getProperty());
	}
	
	private Expression<Object> toExpressionRecursively(Path<Object> path, PropertyPath property) {
		Path<Object> result = path.get(property.getSegment());
		return property.hasNext() ? toExpressionRecursively(result, property.next()) : result;
	}
	@SuppressWarnings("unchecked")
	private <Y> Expression<Y> toExpressionRecursively(From<?, ?> from, PropertyPath property) {
		if (property.isCollection()) {
			Join<Object, Object> join = from.join(property.getSegment());
			return (Expression<Y>)(property.hasNext() ? toExpressionRecursively((From<?, ?>) join, property.next()) : join);
		} else {
			Path<Object> path = from.get(property.getSegment());
			return (Expression<Y>)(property.hasNext() ? toExpressionRecursively(path, property.next()) : path);
		}
	}
	/*
	@SuppressWarnings("unchecked")
	private <Y> Expression<Y> upperIfIgnoreCase(Expression<? extends Y> expression, Part part, CriteriaBuilder builder) {
		switch (part.shouldIgnoreCase()) {
		case ALWAYS:
			Assert.state(canUpperCase(expression), "Unable to ignore case of " + expression.getJavaType().getName()
					+ " types, the property '" + part.getProperty().getSegment() + "' must reference a String");
			return (Expression<Y>) builder.upper((Expression<String>) expression);
		case WHEN_POSSIBLE:
			if (canUpperCase(expression)) {
				return (Expression<Y>) builder.upper((Expression<String>) expression);
			}
		}
		return (Expression<Y>) expression;
	}
	private boolean canUpperCase(Expression<?> expression) {
		return String.class.equals(expression.getJavaType());
	}
	*/	
	
	

	
}
