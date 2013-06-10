import sys
import csv

csvfilename = sys.argv[1]
texfilename = sys.argv[2] 

# Read in CSV
with open(csvfilename, 'rb') as csvfile:
    csvreader = csv.reader(csvfile, delimiter=',', quotechar='"')
    csvreader.next()
    rows = list()
    for row in csvreader:
        rows.append(row)

# Helper variable to remove double entries
previous = None

# Generate TeX
with open(texfilename, 'w') as texfile:
    for row in sorted(rows, key=lambda r: r[1]):
        category = row[1]
        comment = row[3]

        # Handle hardwired comment
        if comment == "excluded on extraction":
           comment = None

        # Do not list any category twice
        if not category == previous:
            texfile.write('\WikiTaxCategory{'+category+'}')
            texfile.write(' & ')
            if comment is not None:
                texfile.write('\WikiTaxComment{'+comment+'}')
            else:
                texfile.write('\WikiTaxEmptyCell')                
            texfile.write('\WikiTaxNewLine\n')
        previous = category
