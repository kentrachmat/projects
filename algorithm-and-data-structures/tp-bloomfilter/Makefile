PROJECT=tp-bloomfilter
AUTHOR=Benedictus_Kent_Rachmat
PYTHONPATH=./src
export PYTHONPATH
SPHINXBUILD=sphinx-build
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

doc: author
	$(SPHINXBUILD) -c $(CONFIGPATH) -b html $(SOURCEDOC) $(DOC)

archive:
	rm -f $(PROJECT).zip
	zip -r $(PROJECT).zip . -x "sol/*" -x "$(DOC)/*" -x "*~" -x "*.pyc" -x "*.DS_Store" -x "*__MACOSX/*" -x "*__pycache__/*"


author:
	sed -i -e 's/^project =.*/project = "Module $(PROJECT)"/g' conf.py
	sed -i -e 's/^copyright =.*/copyright = "2016-2021, $(AUTHOR), Univ. Lille1"/g' conf.py
	sed -i -e 's/^author =.*/author = "$(AUTHOR)"/g' conf.py
