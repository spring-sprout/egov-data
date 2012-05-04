compile:
	cd egov-data-ibatis; \
  mvn compile
	cd egov-data-hibernate; \
  mvn compile
test:
	cd egov-data-ibatis; \
	mvn test -X
	cd egov-data-hibernate; \
	mvn test -X

.PHONY: test compile
