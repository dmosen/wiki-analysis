package de.uni_koblenz.jgralab.utilities.gui.xdot;

import java.awt.Color;
import java.awt.Font;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.graphmarker.GraphMarker;

public class XDotParser {

	private Color lineColor = Color.black;
	private Color fillColor = Color.black;
	private HashMap<String, Color> colorMap;
	private Font font = null;
	private Graph graph;
	private AttributedElement<?, ?> currentElement;
	private List<XDotShape> shapes;
	private GraphMarker<List<XDotShape>> elementShapes;
	private Rectangle2D bounds;

	public XDotParser(Graph g, GraphMarker<List<XDotShape>> es) {
		graph = g;
		elementShapes = es;
		colorMap = new HashMap<String, Color>();
		colorMap.put("black", Color.BLACK);
		colorMap.put("white", Color.WHITE);
		colorMap.put("red", Color.RED);
		colorMap.put("green", Color.GREEN);
		colorMap.put("blue", Color.BLUE);
		colorMap.put("yellow", Color.YELLOW);
		colorMap.put("cyan", Color.CYAN);
		colorMap.put("magenta", Color.MAGENTA);
		colorMap.put("gray", Color.GRAY);
	}

	final class Lexer {
		final String s;
		final char[] c;
		int p;

		Lexer(String s) {
			this.s = s;
			c = s.toCharArray();
			p = 0;
			consumeWhitespace();
		}

		final void consumeWhitespace() {
			while (p < c.length && Character.isWhitespace(c[p])) {
				++p;
			}
		}

		final char nextChar() {
			if (p >= c.length) {
				return 0;
			}
			char result = c[p++];
			consumeWhitespace();
			return result;
		}

		final int nextInt() {
			if (p >= c.length) {
				throw new NoSuchElementException();
			}
			int b = p;
			while (p < c.length && !Character.isWhitespace(c[p])) {
				++p;
			}
			int result = Integer.parseInt(s.substring(b, p));
			consumeWhitespace();
			return result;
		}

		final double nextDouble() {
			if (p >= c.length) {
				throw new NoSuchElementException();
			}
			int b = p;
			while (p < c.length && !Character.isWhitespace(c[p])) {
				++p;
			}
			double result = Double.parseDouble(s.substring(b, p));
			consumeWhitespace();
			return result;
		}

		final String nextString() {
			if (p >= c.length) {
				throw new NoSuchElementException();
			}
			int l = nextInt();
			consumeWhitespace();
			if (p >= c.length) {
				throw new NoSuchElementException();
			}
			if (c[p] != '-') {
				throw new IllegalArgumentException();
			}
			++p;
			String result = s.substring(p, p + l);
			p += l;
			consumeWhitespace();
			return result;
		}
	}

	private void parseDrawActions(String s) {
		Lexer l = new Lexer(s);
		for (char action = l.nextChar(); action != 0; action = l.nextChar()) {
			switch (action) {
			case 'c':
			case 'C': {
				// Color
				String color = l.nextString();
				Color c = colorMap.get(color);
				if (c == null) {
					if (color.charAt(0) == '#') {

						int r = Integer.parseInt(color.substring(1, 3), 16);
						int g = Integer.parseInt(color.substring(3, 5), 16);
						int b = Integer.parseInt(color.substring(5, 7), 16);
						if (color.length() == 9) {
							int a = Integer.parseInt(color.substring(7, 9), 16);
							c = new Color(r, g, b, a);
						} else {
							c = new Color(r, g, b);
						}
					} else {
						c = Color.black;
					}
					colorMap.put(color, c);
				}
				if (action == 'c') {
					lineColor = c;
				} else {
					fillColor = c;
				}
			}
				break;

			case 'p':
			case 'P':
			case 'L':
			case 'b':
			case 'B': {
				// Polygon, Polyline, B-Spline
				int n = l.nextInt();
				double[] xp = new double[n];
				double[] yp = new double[n];
				for (int i = 0; i < n; ++i) {
					xp[i] = l.nextDouble();
					yp[i] = l.nextDouble();
				}
				XDotShape xs;
				if (action == 'p' || action == 'P' || action == 'L') {
					Path2D p = new Path2D.Double();
					for (int i = 0; i < n - 1; i++) {
						p.append(new Line2D.Double(xp[i], yp[i], xp[i + 1],
								yp[i + 1]), i != 0);
					}
					if (action != 'L') {
						p.closePath();
					}
					xs = new XDotShape(p, lineColor, action == 'P' ? fillColor
							: null, null, null);
				} else {
					Path2D p = new Path2D.Double();
					for (int i = 0; i < n - 1; i += 3) {
						p.append(new CubicCurve2D.Double(xp[i], yp[i],
								xp[i + 1], yp[i + 1], xp[i + 2], yp[i + 2],
								xp[i + 3], yp[i + 3]), i != 0);
					}
					xs = new XDotShape(p, lineColor, action == 'b' ? fillColor
							: null, null, null);
				}
				shapes.add(xs);
				addShapeToCurrentElement(xs);
			}
				break;

			case 'e':
			case 'E': {
				// Ellipse
				double x = l.nextDouble();
				double y = l.nextDouble();
				double w = l.nextDouble();
				double h = l.nextDouble();
				Ellipse2D e = new Ellipse2D.Double(x - w, y - h, w * 2, h * 2);
				XDotShape xs = new XDotShape(e, lineColor,
						action == 'E' ? fillColor : null, null, null);
				shapes.add(xs);
				addShapeToCurrentElement(xs);
			}
				break;

			case 'F': {
				// Font
				double size = l.nextDouble();
				String name = l.nextString();
				font = new Font(name, Font.PLAIN, (int) size);
			}
				break;

			case 'T': {
				// Text
				double x = l.nextDouble();
				double y = l.nextDouble();
				int j = l.nextInt(); // -1=left, 0=centered, 1=right
				double w = l.nextDouble();
				String text = l.nextString();
				TextShape t = new TextShape(x, y, j, w, text, font);
				XDotShape xs = new XDotShape(t, null, null, lineColor, null);
				shapes.add(xs);
				addShapeToCurrentElement(xs);
			}
				break;

			case 'S': {
				// TODO: Style attribute
				@SuppressWarnings("unused")
				String style = l.nextString();
			}
				break;

			default:
				throw new RuntimeException("FIXME: Unknown action '" + action
						+ "'");
			}
		}
	}

	public void addShapeToCurrentElement(XDotShape xs) {
		if (currentElement == null) {
			return;
		}
		xs.setElement(currentElement);
		List<XDotShape> l = elementShapes.get(currentElement);
		if (l == null) {
			l = new ArrayList<XDotShape>();
			elementShapes.mark(currentElement, l);
		}
		l.add(xs);
	}

	public List<XDotShape> parseXDotFile(InputStream is) throws IOException {
		// System.out.println("----- XDOT FILE " + xdotfilename + " -----");
		shapes = new ArrayList<XDotShape>();
		elementShapes.clear();
		bounds = null;
		BufferedReader rdr = new BufferedReader(new InputStreamReader(is,
				"UTF-8"));
		Pattern pd = Pattern
				.compile("(_(l|h|t|hl|tl)?draw_)\\s*=\\s*\"([^\"]*)\"");
		Pattern pi = Pattern.compile("(id\\s*=\\s*|cluster_)(\\w+)");
		Pattern pb = Pattern
				.compile("bb\\s*=\\s*\"(-?\\d+\\.?\\d*),(-?\\d+\\.?\\d*),(-?\\d+\\.?\\d*),(-?\\d+\\.?\\d*)\"");
		setCurrentElement(graph);
		for (String l = rdr.readLine(); l != null; l = rdr.readLine()) {
			StringBuilder sb = new StringBuilder();
			while (l != null && l.endsWith("\\")) {
				sb.append(l.substring(0, l.length() - 1));
				l = rdr.readLine();
			}
			if (l != null) {
				sb.append(l);
			}
			l = sb.toString().replace("\\\"", "\u0001");

			if (bounds == null) {
				Matcher mb = pb.matcher(l);
				if (mb.find()) {
					double x0 = Double.parseDouble(mb.group(1));
					double y0 = Double.parseDouble(mb.group(2));
					double x1 = Double.parseDouble(mb.group(3));
					double y1 = Double.parseDouble(mb.group(4));
					bounds = new Rectangle2D.Double(x0, y0, x1, y1);
				}
			}

			Matcher mi = pi.matcher(l);
			if (mi.find()) {
				String id = mi.group(2);
				if (id.charAt(0) == 'v') {
					setCurrentElement(graph.getVertex(Integer.parseInt(id
							.substring(1))));
				} else if (id.charAt(0) == 'e') {
					setCurrentElement(graph.getEdge(Integer.parseInt(id
							.substring(1))));
				} else {
					throw new RuntimeException("FIXME: unexpected id format '"
							+ id + "'");
				}
			}

			Matcher md = pd.matcher(l);
			while (md.find()) {
				String drawActions = md.group(3).replace("\u0001", "\"");
				// System.out.println("\t" + md.group(1) + ": " + drawActions);
				parseDrawActions(drawActions);
			}
		}
		rdr.close();
		// System.out.println("----- END -----");
		return shapes;
	}

	private void setCurrentElement(AttributedElement<?, ?> el) {
		if (currentElement != el) {
			currentElement = el;
		}
	}

	public Rectangle2D getBounds() {
		return bounds;
	}
}
