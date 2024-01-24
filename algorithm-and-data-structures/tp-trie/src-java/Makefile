all: TrieMain

classes/trie/Trie.class : src/trie/Trie.java
	javac -d classes -cp classes -sourcepath src src/trie/Trie.java

TrieMain : classes/TrieMain.class

classes/TrieMain.class : src/TrieMain.java classes/trie/Trie.class
	javac -d classes -cp classes -sourcepath src src/TrieMain.java

figure1 : classes/TrieMain.class
	java -cp classes TrieMain > test1.dot
	dot -Tpdf test1.dot -otest1.pdf

clean:
	rm -f test?.pdf test?.dot
	rm -f classes/*.class classes/trie/*.class

classes/trie/CompactTrie.class : src/trie/CompactTrie.java
	javac -d classes -cp classes -sourcepath src src/trie/CompactTrie.java

CompactTrieMain : classes/CompactTrieMain.class

classes/CompactTrieMain.class : src/CompactTrieMain.java classes/trie/CompactTrie.class
	javac -d classes -cp classes -sourcepath src src/CompactTrieMain.java

figure2 : classes/CompactTrieMain.class
	java -cp classes CompactTrieMain > test2.dot
	dot -Tpdf test2.dot -otest2.pdf

doc : src/trie/*.java
	javadoc -d
