ARCHIVE_FILES=$(wildcard src/*) Makefile README.md junit5.jar
PACKAGE = banque
DELETE = classes docs

all: cls

doc: 
	cd src/main/java && javadoc -d ../../../docs -subpackages banque

cls:
	cd src/main/java && javac -d ../../../classes $(PACKAGE)/*.java

archive: project.zip

project.zip: $(ARCHIVE_FILES)
	zip $@ $(ARCHIVE_FILES)

clean:
	rm -rf $(DELETE)
	
.PHONY: clean doc cls  

.ONESHELL:
