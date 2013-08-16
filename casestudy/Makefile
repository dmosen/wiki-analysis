ssh-user = softlang@linux.uni-koblenz.de
www-dir = /home/softlang/www/wikitax

all:
	pdflatex paper
	bibtex paper
	pdflatex paper
	pdflatex paper

# Note, the data files are snapshotted now!
derive:
	cd ../scripts; make

ignore:
	svn propedit svn:ignore .

package:
	rm -f paper.zip
	zip paper.zip *.tex paper.bib figures/*.png figures/*.pdf data/*.tex* llncs.cls splncs03.bst

clean:
	rm -f *.aux paper.pdf *.bbl *.pdf *.ps *.blg *.log *.out paper.zip

upload: all create-webdir
	scp -r -p index.html paper.pdf ${ssh-user}:${www-dir}

upload-html: create-webdir
	scp -r -p index.html ${ssh-user}:${www-dir}

create-webdir:
	ssh ${ssh-user} mkdir -p ${www-dir}
