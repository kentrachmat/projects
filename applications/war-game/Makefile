ARCHIVE_FILES=$(wildcard jar/* test/* documents/* src/*) manifesthumanwar manifesthumanfarm manifestfarm manifestwar Makefile README.md
PACKAGE = game
DELETE = classes docs

all: cls

test:
	javac -classpath test.jar test/game/tile/resources/*.java
	javac -classpath test.jar test/game/tile/*.java
	javac -classpath test.jar test/game/player/*.java
	javac -classpath test.jar test/game/personnage/*.java
	javac -classpath test.jar test/game/board/*.java
	javac -classpath test.jar test/game/action/*.java

doc: 
	cd src && javadoc -d ../docs -subpackages game

cls:
	cd src && javac -d ../classes $(PACKAGE)/*.java

guerre:
	cd classes && jar cvfm ../jar/guerre.jar ../manifestwar game

agricole:
	cd classes && jar cvfm ../jar/agricole.jar ../manifestfarm game

guerreHuman:
	cd classes && jar cvfm ../jar/guerreHuman.jar ../manifesthumanwar game

agricoleHuman:
	cd classes && jar cvfm ../jar/agricoleHuman.jar ../manifesthumanfarm game

archive: projectG6.zip

projectG6.zip: $(ARCHIVE_FILES)
	zip $@ $(ARCHIVE_FILES)

clean:
	rm -r $(DELETE)
	
.PHONY: all clean doc cls

.ONESHELL:
