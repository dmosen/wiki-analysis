ssh-user = softlang@linux.uni-koblenz.de
www-dir = /home/softlang/www/wikitax

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

upload: all create-webdir
	scp -r -p index.html paper.pdf ${ssh-user}:${www-dir}

upload-html: create-webdir
	scp -r -p index.html ${ssh-user}:${www-dir}

create-webdir:
	ssh ${ssh-user} mkdir -p ${www-dir}
