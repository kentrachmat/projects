DELETE = code/*_output.txt code/__pycache__

gsm:
	cd code && python3 gsm.py -i data/gsm_data.txt -o gsm_output.txt

map:
	cd code && python3 map.py -i data/map_data.txt -o map_output.txt

sudoku:
	cd code && python3 sudoku.py -i data/sudoku_data.txt -o sudoku_output.txt

clean:
	rm -rf $(DELETE)

.PHONY: clean