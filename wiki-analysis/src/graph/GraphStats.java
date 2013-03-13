package graph;

/**
 * 
 * @author dmosen@uni-koblenz.de
 *
 */
public class GraphStats {

	private int pageLinks;

	private int subcategoryLinks;
	
	private int maxPages;

	private int maxTransitivePages;

	private int maxSubcategories;

	private int maxTransitiveSubcategories;

	private int maxParentCategories;

	private int pagesQuantil25;
		
	private int pagesMedian;
	
	private int pagesQuantil75;
	
	private double pagesMean;

	private int subcategoriesQuantil25;
		
	private int subcategoriesMedian;

	private int subcategoriesQuantil75;
		
	private double subcategoriesMean;

	public GraphStats() {
		setPageLinks(0);
		setSubcategoryLinks(0);
		maxPages = 0;
		maxTransitivePages = 0;
		maxSubcategories = 0;
		maxTransitiveSubcategories = 0;
		maxParentCategories = 0;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public int getMaxParentCategories() {
		return maxParentCategories;
	}

	public int getMaxSubcategories() {
		return maxSubcategories;
	}

	public int getMaxTransitivePages() {
		return maxTransitivePages;
	}

	public int getMaxTransitiveSubcategories() {
		return maxTransitiveSubcategories;
	}

	public double getPagesMean() {
		return pagesMean;
	}

	public int getPagesQuantil25() {
		return pagesQuantil25;
	}

	public int getPagesMedian() {
		return pagesMedian;
	}
	
	public int getPagesQuantil75() {
		return pagesQuantil75;
	}

	public double getSubcategoriesMean() {
		return subcategoriesMean;
	}
	
	public int getSubcategoriesQuantil25() {
		return subcategoriesQuantil25;
	}
	
	public int getSubcategoriesMedian() {
		return subcategoriesMedian;
	}

	public int getSubcategoriesQuantil75() {
		return subcategoriesQuantil75;
	}
	
	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}

	public void setMaxParentCategories(int maxParentCategories) {
		this.maxParentCategories = maxParentCategories;
	}

	public void setMaxSubcategories(int maxSubcategories) {
		this.maxSubcategories = maxSubcategories;
	}

	public void setMaxTransitivePages(int maxTransitivePages) {
		this.maxTransitivePages = maxTransitivePages;
	}

	public void setMaxTransitiveSubcategories(int maxTransitiveSubcategories) {
		this.maxTransitiveSubcategories = maxTransitiveSubcategories;
	}

	protected void setPagesMean(double pagesMean) {
		this.pagesMean = pagesMean;
	}

	protected void setPagesQuantil25(int pagesQuantil25) {
		this.pagesQuantil25 = pagesQuantil25;
	}
	
	protected void setPagesMedian(int pagesMedian) {
		this.pagesMedian = pagesMedian;
	}
	
	protected void setPagesQuantil75(int pagesQuantil75) {
		this.pagesQuantil75 = pagesQuantil75;
	}

	protected void setSubcategoriesMean(double subcategoriesMean) {
		this.subcategoriesMean = subcategoriesMean;
	}

	protected void setSubcategoriesQuantil25(int subcategoriesQuantil25) {
		this.subcategoriesQuantil25 = subcategoriesQuantil25;
	}
	
	protected void setSubcategoriesMedian(int subcategoriesMedian) {
		this.subcategoriesMedian = subcategoriesMedian;
	}

	protected void setSubcategoriesQuantil75(int subcategoriesQuantil75) {
		this.subcategoriesQuantil75 = subcategoriesQuantil75;
	}
	
	public int getPageLinks() {
		return pageLinks;
	}

	protected void setPageLinks(int pageLinks) {
		this.pageLinks = pageLinks;
	}

	public int getSubcategoryLinks() {
		return subcategoryLinks;
	}

	protected void setSubcategoryLinks(int subcategoryLinks) {
		this.subcategoryLinks = subcategoryLinks;
	}
}
