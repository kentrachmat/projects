DELETE = exo1/*.class exo2/*.class exo3/*.class docs

all: exo1 exo2 exo3 
	
exo1:
	javac exo1/*.java

exo2:
	javac exo2/*.java

exo3:
	javac exo3/*.java

doc:
	javadoc -d docs -subpackages exo1 exo2 exo3

clean:
	rm -rf $(DELETE)

.PHONY: exo1 exo2 exo3 clean doc