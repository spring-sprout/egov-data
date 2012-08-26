# eGov Data [![Build Status](https://secure.travis-ci.org/spring-sprout/egov-data.png?branch=master)](http://travis-ci.org/spring-sprout/egov-data)

eGov Data 프로젝트 빌드하기

## 메이븐 ##

* 이 프로젝트는 빌드툴로 메이븐을 사용합니다.
* multi pom 프로젝트로 구성되어 있습니다.

.
├── pom.xml
│
├── egov-data-hibernate
│   ├── pom.xml
│   └── src
│
├── egov-data-ibatis
│   ├── pom.xml
│   └── src
│
├── egov-data-hibernate-sample
│   ├── pom.xml
│   └── src
│
└── egov-data-ibatis
    ├── pom.xml
    └── src


최상위 pom.xml은 현재 travis 빌드를 간편하게 하도록 생략했지만, IDE에서 편하게 사용하시려면 필요할 수 있습니다. 그럴 떄는 ./maven/pom.xml 파일을 프로젝트 루트로 복사해서 사용하시면 됩니다.