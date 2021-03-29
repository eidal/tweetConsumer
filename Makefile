.DEFAULT_GOAL := help

# AutoDoc
# -------------------------------------------------------
define PRINT_HELP_PYSCRIPT
import re, sys
for line in sys.stdin:
	match = re.match(r'^([a-zA-Z0-9_-]+):.*?## (.*)$$', line)
	if match:
		target, help = match.groups()
		print("%-20s %s" % (target,help))
endef
export PRINT_HELP_PYSCRIPT

.PHONY: help
help:
	@python3 -c "$$PRINT_HELP_PYSCRIPT" < $(MAKEFILE_LIST)


#Build section
# -------------------------------------------------------
.PHONY: build
build: ## builds the application
	mvn clean install

.PHONY: build-notest
build-notest: ## build the application without run tests
	mvn clean install -DskipTests

.PHONY: build-dependency
build-dependency: ## builds the application clean dependencies
	mvn clean install -U

# Run section
# -------------------------------------------------------
PHONY: build-run
build-run: ## runs the application with build
	mvn clean spring-boot:run

PHONY: run
run: ## runs the application
	mvn spring-boot:run


PHONY: debug
debug: ## debug the application on port 5005
	mvn clean spring-boot:run -Dspring-boot.run-jvmArguments="-Xdebug -Xrnjdwp:transport=dt_socket,server=y,suspend=y,address=5505"

# Tests section
# -------------------------------------------------------
.PHONY: test
test: ## tests de application
	mvn clean test

# Infraestructure section
# -------------------------------------------------------
.PHONY: infraestructure-up
infraestructure-up: ## run containerized infraestructure
	docker-compose up -d
.PHONY: infraestructure-down
infraestructure-down: ## run containerized infraestructure
	docker-compose down