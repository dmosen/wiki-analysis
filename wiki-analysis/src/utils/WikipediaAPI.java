package utils;

import info.bliki.api.Connector;
import info.bliki.api.Page;
import info.bliki.api.PageInfo;
import info.bliki.api.User;
import info.bliki.api.XMLCategoryMembersParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xml.sax.SAXException;

/**
 * This class provides convenience functions for accessing Wikipedia content
 * through the Bliki API (https://code.google.com/p/gwtwiki/)
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
public class WikipediaAPI {

	private final static User user = new User("", "",
			"http://en.wikipedia.org/w/api.php");

	private final static Connector connector = new Connector();

	/**
	 * Returns a list of strings of direct subcategories for a given category
	 * string <br>
	 * <br>
	 * i.e. <code>"Category:Computer_languages"</code>
	 * 
	 * @param category
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static List<String> getSubCategories(String category)
			throws SAXException, IOException, InterruptedException {
		return getCategoryMembers(category, Cmtype.SUBCAT);
	}

	public static boolean isValidPage(String title) {
		List<Page> pages = connector.queryInfo(user,
				Arrays.asList(new String[] { title }));
		if (!pages.isEmpty()) {
			Page page = pages.get(0);
			if (page.getPageid() != null) {
				return true;
			}
		}
		return false;
	}

	public static List<String> getPages(String category) throws SAXException,
			IOException, InterruptedException {
		return getCategoryMembers(category, Cmtype.PAGE);
	}

	public static Integer getPageCountInCategory(String category)
			throws SAXException, IOException, InterruptedException {
		return getPages(category).size();
	}

	public static List<String> getCategoryMembers(String category, Cmtype cmtype)
			throws SAXException, IOException, InterruptedException {
		List<String> result = new ArrayList<String>();
		String cmcontinue = "";

		do {
			String[] query = new String[] { "list", "categorymembers",
					"cmtitle", category, "cmtype", cmtype.id, "cmlimit", "max",
					"cmcontinue", cmcontinue };

			String rawXmlResponse = processQuery(query);

			XMLCategoryMembersParser parser = new XMLCategoryMembersParser(
					rawXmlResponse);

			parser.parse();

			List<PageInfo> pages = parser.getPagesList();

			for (PageInfo p : pages) {
				result.add(p.getTitle());
			}

			cmcontinue = parser.getCmContinue();
		} while (cmcontinue != "");

		return result;
	}

	private static String processQuery(String[] query)
			throws InterruptedException {
		int i = 0;
		String rawXmlResponse = null;

		do {
			if (i != 0) {
				System.out.println("Connection Error, sleeping 1000 ms.");
				Thread.sleep(1000);
			}
			rawXmlResponse = connector.queryXML(user, query);
		} while (rawXmlResponse == null && i < 3);
		return rawXmlResponse;
	}
}