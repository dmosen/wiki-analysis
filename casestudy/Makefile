all: derive
	pdflatex paper
	bibtex paper
	pdflatex paper
	pdflatex paper

derive:
	cd ../scripts; make

ignore:
	svn propedit svn:ignore .

clean:
	rm -f *.aux *.pdf *.bbl *.pdf *.ps *.blg *.log *.out
