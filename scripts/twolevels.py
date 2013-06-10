import sys
import json

jsonfilename = sys.argv[1]
texfilename = sys.argv[2] 
jsonfile = open(jsonfilename, 'rb')
cgraph = json.load(jsonfile)

level1 = dict()
level2 = dict()
sub = dict()

# Gather level 1 and 2 nodes
for entry in cgraph:
    if entry["type"] == "Category":
        id = entry["id"]
        title = entry["title"]
        level = entry["level"]
        if level == 1:
            level1[id] = title
            sub[title] = []
        elif level == 2:
            level2[id] = title

# Gather list of subcategories
for entry in cgraph:
    if entry["type"] == "HasSubcategory":

        # Determine whether the edge should contribute
        skip = entry["excluded"] or entry["blacklisted"]
        x = entry["start"]
        y = entry["end"]

        # Remove level 1 categories that should be skipped
        if y in level1 and entry["blacklisted"]:
            del sub[level1[y]]

        # Count the edge possibly
        if x in level1 and y in level2 and not skip:
            sub[level1[x]].append(level2[y])

# Sort at both levels
rows = list()
for cat in sub:
    rows.append((cat, sorted(sub[cat])))
rows = sorted(rows, key=lambda x: x[0])

# Generate TeX
with open(texfilename, 'w') as texfile:
    for entry in rows:
        category = entry[0]
        subcategories = entry[1]
        texfile.write('\WikiTaxCategory{'+category+'}')
        texfile.write(' & ')
        if len(subcategories) == 0:
            texfile.write('\WikiTaxEmptyCell')
        else:
            first = True
            for sub in subcategories:
                if not first:
                    texfile.write(', ')
                texfile.write('\WikiTaxSubcategory{'+sub+'}')
                first = False
        texfile.write('\WikiTaxNewLine\n')
