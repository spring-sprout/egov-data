test:
	cd egov-data-ibatis; \
	mvn test
	cd egov-data-hibernate; \
	mvn test

.PHONY: test
