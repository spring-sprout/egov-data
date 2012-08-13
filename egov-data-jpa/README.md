that-data-jpa 
=================================

[SPRING DATA - JPA](http://www.springsource.org/spring-data/jpa)�� ������� Custom Repository �� ����


## �ֿ� ��� ##

* �⺻ CRUD �޼��� ���� ����.
* [Query Method](http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/#jpa.query-methods)�� �������� ���� ���� 

## ���� �ϱ� ##

���̺쿡 ������ �߰��ϱ�:
```xml
  		<!-- Spring ::: data -->
		<dependency>
			<groupId>org.springframework.data</groupId>
			<artifactId>spring-data-jpa</artifactId>
			<version>${org.springframework.data-version}</version>
		</dependency>
		
      	<!-- @jpa -->
		<dependency>
		    <groupId>org.hibernate.javax.persistence</groupId>
		    <artifactId>hibernate-jpa-2.0-api</artifactId>
		    <version>1.0.0.Final</version>
		</dependency>

      	<!-- @jpa implementation-->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>3.6.9.Final</version>
			<scope>runtime</scope>
		</dependency>
```

JPA entity �ۼ��ϱ� :

```java
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String firstname;
  private String lastname;

  // Getters and setters
}
```

JPA Repository interface �ۼ��ϱ� 
[EntityRepository](https://github.com/u2waremanager/that-data-jpa/blob/master/egov-data-jpa/src/main/java/egov/data/jpa/repository/EntityRepository.java)
��� :

```java
public interface UserRepository extends EntityRepository<User, Long> {
}
```
Spring Context �����ϱ�:
```xml

	<!-- datasource :  -->
	<jdbc:embedded-database id="dataSource" type="HSQL" />


 	<!-- persistenceUnitManager :  META-INF/persistence.xml ���Ͼ��� ���� ���� -->
	<bean id="pum" class="org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager">
 		<property name="defaultDataSource" ref="dataSource"/>
 		<property name="defaultPersistenceUnitName" value="em"/>
 		<property name="defaultPersistenceUnitRootLocation" value="classpath:egov/data/jpa/sample"/>
 		<property name="packagesToScan" value="egov.data.jpa.sample"/>
 	</bean>

	<!-- entityManager : -->
  	<bean id="em" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
		<property name="persistenceUnitManager" ref="pum" /> 
		<property name="persistenceUnitName" value="em" />
		<property name="jpaDialect">
			<bean class="org.springframework.orm.jpa.vendor.HibernateJpaDialect" />
		</property>
		<property name="jpaVendorAdapter">
			<bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter"/>
		</property>
		<property name="jpaPropertyMap">
			<map>
				<entry key="hibernate.cache.provider_class" value="org.hibernate.cache.HashtableCacheProvider"/>
				<entry key="hibernate.dialect" value="org.hibernate.dialect.HSQLDialect"/>
				<entry key="hibernate.hbm2ddl.auto" value="update" />
				<entry key="hibernate.show_sql" value="true" />
				<entry key="hibernate.format_sql" value="true" />
			</map>
		</property>
	</bean>
	
	<!-- 'transactionManager' is default value of @Transactional -->
	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
	    <property name="entityManagerFactory" ref="em"/>
	</bean>
	<tx:annotation-driven transaction-manager="transactionManager"/>


	<!-- repositories 	-->
	<jpa:repositories base-package="egov.data.jpa.sample"
					factory-class="egov.data.jpa.repository.support.EntityRepositoryFactoryBean" 
					transaction-manager-ref="transactionManager"  
					entity-manager-factory-ref="em"/>

```

## ��� �ϱ� ##

Repository Interface �� Query Method �� ������ �������� �ʾƵ� Query Method Name �� ����� �� �ִ�.
Parameter �� �Ѱ��� ��ü�� property ����  Query Method �� ������Ű�� ����̸� 
property �� null �� ���� Query Method Name �� �������� �����Ѵ�.

```java
	@Test
	public void queryMethod1() {
	
		assertThat(userRepository.findAll().size(), is(0));
		userRepository.save(new User("ȫ", "�浿" , "a1@google.com" , 16));
		userRepository.save(new User("��", "�浿" , "a2@google.com", 24));
		userRepository.save(new User("��", "ö��" , "a1@twitter.com", 21));
		userRepository.save(new User("��", "����" , "a2@twitter.com", 50));
		List<User> users = userRepository.findAll();
		assertThat(users.size(), is(4));
		
		User param = new User();

		param.setFirstname("��");
		param.setLastname(null);
		List<User> users1 = userRepository.findAll("findByFirstnameAndLastname" , param);
		assertThat(users1.size(), is(2)); // ----(1) 'findByFirstname'

	
		param.setFirstname(null);
		param.setLastname("����");
		List<User> users2 = userRepository.findAll("findByFirstnameAndLastname" , param);
		assertThat(users2.size(), is(1)); // ----(2) 'findByLastname'
	

		param.setFirstname(null);
		param.setLastname(null);
		List<User> users3 = userRepository.findAll("findByFirstnameAndLastname" , param);
		assertThat(users3.size(), is(4));// ----(3) 'findAll'
	}
```

## ���� �ϱ� ##

[EntityRepository](https://github.com/u2waremanager/that-data-jpa/blob/master/egov-data-jpa/src/main/java/egov/data/jpa/repository/EntityRepository.java)
�� 
[QueryMethodExecutor](https://github.com/u2waremanager/that-data-jpa/blob/master/egov-data-jpa/src/main/java/egov/data/jpa/repository/QueryMethodExecutor.java)
�� ����Ѵ�.

```java
public interface QueryMethodExecutor<T> {

	public List<T> findAll(String queryMethodName, Object param);
	public List<T> findAll(String queryMethodName, Object param, Sort sort);
	public Page<T> findAll(String queryMethodName, Object param, Pageable pageable);

	public List<T> findAll(Object param);
	public List<T> findAll(Object param, Sort sort);
	public Page<T> findAll(Object param, Pageable pageable);
	
}
```
queryMethodName �� �ѱ��� ��������, param ��ü�� Class �̸��� queryMethodName �� ����Ѵ�. :

```java
	@Test
	public void queryMethod2() {

		FindByEmailAddressContaining param = new FindByEmailAddressContaining();
		param.setEmailAddress("a1");
		
		List<User> users = userRepository.findAll(param);
		assertThat(users.size(), is(2));
	}

	public class FindByEmailAddressContaining {

		private String emailAddress;

		// Getter ,Setter		
	}
```

param Ŭ������  [QueryMethod](https://github.com/u2waremanager/that-data-jpa/blob/master/egov-data-jpa/src/main/java/egov/data/jpa/repository/QueryMethod.java)
Annotation �� �߰� �� ���� �ִ�.

```java
	@Test
	public void queryMethod3() {
		UserParam param = new UserParam();
		param.setAgeMin(20);
		param.setAgeMax(30);
		
		List<User> users = userRepository.findAll(param);
		assertThat(users.size(), is(2));
	}

	@QueryMethod("findByAgeBetween")
	public class UserParam {

		private int ageMax;
		private int ageMin;
		
		// Getter ,Setter		
	}
```