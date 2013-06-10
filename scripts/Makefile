# Build some .tex files from the data files

cl2 = ../data/Computer_languages

all:
	python metaclassify.py ${cl2}.csv ${cl2}.tex-metaclassify
	python twolevels.py ${cl2}.json ${cl2}.tex-twolevels

clean:
	rm -f ../data/*.tex*
