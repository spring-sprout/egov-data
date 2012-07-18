.PHONY: test
test:
	cd egov-data-ibatis; \
	mvn test
	cd egov-data-hibernate; \
	mvn test

.PHONY: deploy
deploy:
	cd egov-data-hibernate; \
	mvn clean deploy
	cd egov-data-ibatis; \
	mvn clean deploy
