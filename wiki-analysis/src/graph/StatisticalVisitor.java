package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Vertex;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class StatisticalVisitor implements BlacklistedGraphDFSVisitor {

	private GraphProperties gp;
	private GraphStats graphStats;

	private Vertex root;

	private boolean ignoreEmptyCategories;
	
	private int pages;
	private int categories;

	private int pageLinks;
	private List<Integer> pagesList = new ArrayList<Integer>();

	private int subcategoryLinks;
	private List<Integer> subcategoriesList = new ArrayList<Integer>();

	public GraphStats getGraphStats() {
		return graphStats;
	}

	public StatisticalVisitor(boolean ignoreEmptyCategories) {
		this.ignoreEmptyCategories = ignoreEmptyCategories;
	}

	@Override
	public void visitRoot(Vertex v) {
		root = v;

		gp = GraphProperties.getInstance();

		pages = 0;
		categories = 0;
		
		pageLinks = 0;
		subcategoryLinks = 0;

		graphStats = new GraphStats();
	}

	@Override
	public void visitEdge(Edge e) {
		// count page links
		if (e.isInstanceOf(gp.pageLinkEC)) {
			pageLinks++;
		}

		// count sub category links
		if (e.isInstanceOf(gp.subCategoryLinkEC)) {
			subcategoryLinks++;
		}
	}

	@Override
	public void visitVertex(Vertex v) {
		if (v.isInstanceOf(gp.categoryNodeVC)) {
			categories++;
			
			// count in-links from parent categories which are not blacklisted
			int parentCategories = 0;
			for (Edge e : v.incidences(gp.subCategoryLinkEC, EdgeDirection.IN)) {
				if (!(Boolean) e.getAttribute("blacklisted")) {
					parentCategories++;
				}
			}

			// count out-links to sub categories which are not blacklisted
			int subCategories = 0;
			for (Edge e : v.incidences(gp.subCategoryLinkEC, EdgeDirection.OUT)) {
				if (!(Boolean) e.getAttribute("blacklisted")) {
					subCategories++;
				}
			}

			// count pages contained in the current category
			int pages = v.getDegree(gp.pageLinkEC, EdgeDirection.OUT);

			// set values for the current vertex
			v.setAttribute("superCategories", parentCategories);
			v.setAttribute("subCategories", subCategories);
			v.setAttribute("pages", pages);

			graphStats.setMaxPages(Math.max(pages, graphStats.getMaxPages()));
			graphStats.setMaxSubcategories(Math.max(subCategories,
					graphStats.getMaxSubcategories()));
			graphStats.setMaxParentCategories(Math.max(parentCategories,
					graphStats.getMaxParentCategories()));

			pagesList.add(pages);

			subcategoriesList.add(subCategories);
		}
		
		if (v.isInstanceOf(gp.pageNodeVC)) {
			pages++;
		}
	}

	@Override
	public void leaveVertex(Vertex v) {
		if (v.equals(root)) {
			computeGraphStats(ignoreEmptyCategories);
		}
	}

	@Override
	public void visitTreeEdge(Edge e) {
	}

	private GraphStats computeGraphStats(boolean ignoreEmptyCategories) {
		graphStats.setPageLinks(pageLinks);
		graphStats.setSubcategoryLinks(subcategoryLinks);
		
		graphStats.setPages(pages);
		graphStats.setCategories(categories);
				
		Collections.sort(pagesList);
		Collections.sort(subcategoriesList);

		if (ignoreEmptyCategories) {
			pagesList = removeZeros(pagesList);
			subcategoriesList = removeZeros(subcategoriesList);
		}

		graphStats.setPagesQuantil25(pagesList.get(pagesList.size() / 4));
		graphStats.setPagesMedian(pagesList.get(pagesList.size() / 2));
		graphStats.setPagesQuantil75(pagesList.get((pagesList.size() / 4) * 3));

		graphStats.setSubcategoriesQuantil25(subcategoriesList
				.get(subcategoriesList.size() / 4));
		graphStats.setSubcategoriesMedian(subcategoriesList
				.get(subcategoriesList.size() / 2));
		graphStats.setSubcategoriesQuantil75(subcategoriesList
				.get((subcategoriesList.size() / 4) * 3));

		graphStats.setPagesMean(pageLinks / (double) pagesList.size());
		graphStats.setSubcategoriesMean(subcategoryLinks
				/ (double) pagesList.size());
		return graphStats;
	}

	private List<Integer> removeZeros(List<Integer> l) {
		int i = 0;
		while (i < l.size() && l.get(i) == 0) {
			i++;
		}
		if (i < l.size()) {
			return l.subList(i, l.size());
		}
		return l;
	}

}