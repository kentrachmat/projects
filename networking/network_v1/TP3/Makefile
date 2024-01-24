DELETE = exo1/*.class exo2/*.class docs exo1/*.txt exo2/*.txt

all: exo1 exo2 
	
exo1:
	javac exo1/*.java

exo2:
	javac exo2/*.java 

doc:
	javadoc -d docs -subpackages exo1 exo2

clean:
	rm -rf $(DELETE)

.PHONY: exo1 exo2 clean doc