PROJECT=tp-experimentateur
AUTHOR=Aziz__BOURAHMA--Benedictus__KENT__RACHMAT
PYTHONPATH=./src
export PYTHONPATH
SPHINXBUILD=python3 -m sphinx
CONFIGPATH=.
SOURCEDOC=sourcedoc
DOC=doc

.PHONY: clean doc archive author

clean:
	rm -f *~ */*~
	rm -rf __pycache__ src/__pycache__
	rm -rf $(DOC)
	rm -f $(PROJECT).zip
	rm -f conf.py-e
	rm -f */*.pyc
	rm -rf .DS_Store

doc: author
	$(SPHINXBUILD) -c $(CONFIGPATH) -b html $(SOURCEDOC) $(DOC)

archive:
	rm -f $(PROJECT).zip
	zip -r $(PROJECT).zip . -x "sol/*" -x "$(DOC)/*" -x "*~" -x "*.pyc" -x "*.DS_Store" -x "*__MACOSX/*" -x "*__pycache__/*"

author:
	sed -i -e 's/^project =.*/project = "Module $(PROJECT)"/g' conf.py
	sed -i -e 's/^copyright =.*/copyright = "2015-2021, $(AUTHOR), Univ. Lille"/g' conf.py
	sed -i -e 's/^author =.*/author = "$(AUTHOR)"/g' conf.py

dat: src/test_pires.py
	python3 src/test_pires.py 10 > src/tp1-10.dat
	python3 src/test_pires.py 20 > src/tp1-20.dat
	python3 src/test_pires.py 30 > src/tp1-30.dat
	python3 src/test_pires.py 40 > src/tp1-40.dat
	python3 src/test_pires.py 50 > src/tp1-50.dat
	python3 src/test_pires.py 60 > src/tp1-60.dat
	python3 src/test_pires.py 70 > src/tp1-70.dat
	python3 src/test_pires.py 80 > src/tp1-80.dat
	python3 src/test_pires.py 90 > src/tp1-90.dat
	python3 src/test_pires.py 100 > src/tp1-100.dat
	python3 src/test_pires.py 200 > src/tp1-200.dat
	python3 src/test_pires.py 500 > src/tp1-500.dat