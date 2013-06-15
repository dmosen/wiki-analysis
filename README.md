## Introducing Wiki-Analysis

As Wikipedia provides much information on almost every topic and uses different means of 
structuring its information it can be used as a basis to create taxonomies. Wiki-Analysis 
is a tool which helps to build such taxonomies based on the Wikipedia category graph.

The process of bulding taxonomies with Wiki-Analysis is essentially done in two steps:
* Firstly, a part of the Wikipedia category graph is extracted.
* Secondly, the extracted graph is reduced by repeatedly removing categories until a 
satisfying result is reached.

## Documentation and experience

See the manual (subfolder manual) for details regarding architecture and usage.

See the case-study paper (subfolder casestudy) an explorative study of Wikipedia's categories for computer and programming languages.

## Running Wiki-Analysis

Recommended prerequisites:
* JDK 6 or higher
* Eclipse IDE 
    * Tested with Eclipse 4.3 for Java developers
    * Maven support (included in Eclipse for Java developers)
    * Git support (included in Eclipse for Java developers or use external client)

To run Wiki-Analysis, import the three projects (common, jgralab, wiki-analysis) contained in this repository (git://github.com/dmosen/wiki-analysis.git) into Eclipse and execute 
"ApplicationView.java" contained in the project "wiki-analysis" in the package
"visualisation.view".
