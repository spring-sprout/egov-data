# eGov Data Hibernate #

[Spring Data](http://www.springsource.org/spring-data) 프로젝트의 주요 목표는 스프링 기반 애플리케이션이 데이터 접근 기술을 보다 손쉽게 사용할 수 있게 해주는 것입니다. 이 모듈은 표준프레임워크에서 사용하는 Hibernate 기반 데이터 접근 계층에 [Spring Data](http://www.springsource.org/spring-data)의 핵심 기능을 제공합니다.

## 주요 기능 ##

* 하이버네이트 기반 CRUD 메서드 구현 제공.
* 쿼리 메서드 이름을 사용한 동적 쿼리 생성.
* 커스텀 리포지토리 코드 연동.
* 커스텀 네임스페이스 제공.

## 도움이 필요하다면 ##

[Spring Data](http://www.springsource.org/spring-data)의 소스 코드, JavaDocs, 이슈 관리 등의 기본 프로젝트 정보 참고.

[예제 프로젝트](https://github.com/spring-sprout/egov-data/tree/master/egov-data-hibernate-sample)에서 손쉽게 사용법을 익힐 수 있습니다.

## 시작하기 ##

메이븐에 의존성 추가하기:
```xml
<dependency>
  <groupId>org.springframework.data</groupId>
  <artifactId>spring-data-jpa</artifactId>
  <version>1.0.0.BUILD-SNAPSHOT</version>
</dependency>

<repository>
  <id>spring-maven-snapshot</id>
  <snapshots>
    <enabled>true</enabled>
  </snapshots>
  <name>Springframework Maven SNAPSHOT Repository</name>
  <url>http://maven.springframework.org/snapshot</url>
</repository>
```

Also include your JPA persistence provider of choice (Hibernate, EclipseLink, OpenJpa). Setup basic Spring JPA configuration as well as Spring Data JPA repository support.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:jdbc="http://www.springframework.org/schema/jdbc"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa"
       xsi:schemaLocation="http://www.springframework.org/schema/jdbc
                           http://www.springframework.org/schema/jdbc/spring-jdbc.xsd
                           http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/data/jpa
                           http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">

  <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <property name="jpaVendorAdapter">
      <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" />
    </property>
  </bean>

  <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactory" />
  </bean>

  <jdbc:embedded-database id="dataSource" type="HSQL" />

  <jpa:repositories base-package="com.acme.repositories" />
</beans>
```

Create an entity:

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

Create a repository interface in `com.acme.repositories`:

```java
public interface UserRepository extends CrudRepository<User, Long> {
  List<User> findByLastname(String lastname);
}
```

Write a test client

```java
@RunWith(SpringJUnit4TestRunner.class)
@ContextConfiguration("classpath:your-config-file.xml")
public class UserRepositoryIntegrationTest {

  @Autowrired UserRepository repository;

  @Test
  public void sampleTestCase() {
    User dave = new User("Dave", "Matthews");
    repository.save(user);

    User carter = new User("Carter", "Beauford");
    repository.save(carter);

    List<User> result = repository.findByLastname("Matthews");
    assertThat(result.size(), is(1));
    assertThat(result, hasItem(dave));
  }
}
```

## 프로젝트에 참여하고 싶다면 ##

여러가지 방법으로 참여할 수 있습니다.

* [봄싹 그룹스](https://groups.google.com/forum/?fromgroups#!forum/springsprout)에 가입하시고 이 프로젝트와 관련된 의견을 남겨주세요. 봄싹에서 이 프로젝트 개발자들과 의사소통 할 수 있습니다.
* 개선할 것이나 버그가 있다면 Github에 [이슈](https://github.com/spring-sprout/egov-data/issues)를 만들어 주세요.
* Github을 사용해서 소셜 코딩을 하고 싶다면 이 프로젝트를 포크하시고 코드를 작성하신 다음 풀 리퀘스르틀 해주세요. 풀 리퀘스트를 하실때는 반드시 누구나 이해할 수 있도록 충분히 친절하게 설명을 남겨주시고 관련된 이슈가 있다면 이슈 번호도 남겨주시기 바랍니다.
* 마지막으로 지속적으로 관심 가지고 보고 싶다면 이 프로젝트를 [Watch](https://github.com/spring-sprout/egov-data/toggle_watch)하시거나 [Fork](https://github.com/spring-sprout/egov-data/fork_select) 해주세요.

## 개발자 ##

* [김정우](https://github.com/miracle0k)
* [박용권](https://github.com/arawn)
* [백기선](https://github.com/keesun)
* [석종일](https://github.com/miracle0k)
* [최윤석](https://github.com/ethdemor)