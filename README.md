wiki-analysis
=============
As Wikipedia provides much information on almost every topic and uses different means of 
structuring its information it can be used as a basis to create taxonomies. Wiki-Analysis 
is a tool which helps to build such taxonomies based on the Wikipedia category graph.

The process of bulding taxonomies with Wiki-Analysis is essentially done in two steps:
* Firstly, a part of the Wikipedia category graph is extracted.
* Secondly, the extracted graph is reduced by subsequently removing categories until a 
satisfying result is reached.

Running Wiki-Analysis
=====================
Recommended prerequisites:
* JDK 6 or higher
* Eclipse IDE with Maven and Git integration (The version for Java developers should be fine.)

To run Wiki-Analysis import the three projects (common, jgralab, wiki-analysis) contained in 
this repository (git://github.com/dmosen/wiki-analysis.git) into Eclipse and execute 
"ApplicationView.java" contained in the project "wiki-analysis" in the package
"visualisation.view".
