package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
	private GraphStats stats;

	private int pageLinks;
	private List<Integer> pagesList = new ArrayList<Integer>();

	private int subcategoryLinks;
	private List<Integer> subcategoriesList = new ArrayList<Integer>();

	private HashSet<Edge> treeEdges = new HashSet<Edge>();

	@Override
	public void visitRoot(Vertex v) {

		gp = GraphProperties.getInstance();

		pageLinks = 0;
		subcategoryLinks = 0;

		stats = new GraphStats();
	}

	@Override
	public void visitEdge(Edge e) {
		// count page links
		if (e.isInstanceOf(gp.pageLinkEC)) {
			pageLinks++;
		}

		// count sub category links
		if (e.isInstanceOf(gp.subCategoryLinkEC)
				&& !(Boolean) e.getAttribute("blacklisted")) {
			subcategoryLinks++;
		}
	}

	@Override
	public void visitVertex(Vertex v) {
		if (v.isInstanceOf(gp.categoryNodeVC)) {
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

			stats.setMaxPages(Math.max(pages, stats.getMaxPages()));
			stats.setMaxSubcategories(Math.max(subCategories,
					stats.getMaxSubcategories()));
			stats.setMaxParentCategories(Math.max(parentCategories,
					stats.getMaxParentCategories()));

			pagesList.add(pages);

			subcategoriesList.add(subCategories);
		}
	}

	@Override
	public void leaveVertex(Vertex v) {
	}

	public GraphStats computeGraphStats() {
		Collections.sort(pagesList);
		Collections.sort(subcategoriesList);

		stats.setPagesQuantil25(pagesList.get(pagesList.size() / 4));
		stats.setPagesMedian(pagesList.get(pagesList.size() / 2));
		stats.setPagesQuantil75(pagesList.get((pagesList.size() / 4) * 3));

		stats.setSubcategoriesQuantil25(subcategoriesList.get(subcategoriesList
				.size() / 4));
		stats.setSubcategoriesMedian(subcategoriesList.get(subcategoriesList
				.size() / 2));
		stats.setSubcategoriesQuantil75(subcategoriesList
				.get((subcategoriesList.size() / 4) * 3));

		stats.setPagesMean(pageLinks / (double) pagesList.size());
		stats.setSubcategoriesMean(subcategoryLinks / (double) pagesList.size());
		return stats;
	}

	@Override
	public void visitTreeEdge(Edge e) {
		treeEdges.add(e);
	}

}