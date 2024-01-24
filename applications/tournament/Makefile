ARCHIVE_FILES=$(wildcard jar/* test/* documents/* manifest/* src/*) Makefile README.md junit5.jar
PACKAGE = competition
DELETE = classes docs jar

all: cls

doc: 
	cd src && javadoc -d ../docs -subpackages competition

cls:
	cd src && javac -d ../classes $(PACKAGE)/*.java

jar: league tournament competition master

master:
	mkdir -p jar
	cd classes && jar cvfm ../jar/master.jar ../manifest/manifestMaster competition io

league:
	mkdir -p jar
	cd classes && jar cvfm ../jar/league.jar ../manifest/manifestLeague competition

tournament:
	mkdir -p jar
	cd classes && jar cvfm ../jar/tournament.jar ../manifest/manifestTournament competition

competition:
	mkdir -p jar
	cd classes && jar cvfm ../jar/competition.jar ../manifest/manifestCompetition competition io

archive: project.zip

project.zip: $(ARCHIVE_FILES)
	zip $@ $(ARCHIVE_FILES)

clean:
	rm -rf $(DELETE)
	
.PHONY: clean doc cls competition tournament league master

.ONESHELL:
