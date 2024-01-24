
figure1 : trie.py testtrie.py
	python3 testtrie.py > test1.dot
	dot -Tpdf test1.dot -o test1.pdf


figure2 : compacttrie.py compactrietest.py
	python3 compacttrietest.py > test2.dot
	dot -Tpdf test2.dot -o test2.pdf


clean:
	rm -f test?.pdf test?.dot
	rm -rf __pycache__ *.pyc *~
