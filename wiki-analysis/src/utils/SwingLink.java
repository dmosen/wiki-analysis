package utils;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * @author dmosen@uni-koblenz.de
 * 
 */
@SuppressWarnings("serial")
public class SwingLink extends JLabel {
	private String text;
	private String prefix;

	public SwingLink(String text, String prefix) {
		super();
		setup(text, prefix);
	}

	private void setup(String txt, String prfx) {
		this.text = txt;
		this.prefix = prfx;
		setText(text);
		setToolTipText(prefix + text);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				open(prefix + text);
			}
		});
	}

	@Override
	public void setText(String text) {
		super.setText("<html><span style=\"color: #000099;\"><u>" + text
				+ "</u></span></html>");
		this.text = text;
		setToolTipText(prefix + text);
	}

	public String getRawText() {
		return text;
	}
	
	public static void open(String uri) {
		uri = uri.replace(" ", "_");
		try {
			open(new URI(uri));
		} catch (URISyntaxException e) {
			JOptionPane.showMessageDialog(null, "URI conversion",
					"Failed to create a URI from \"" + uri + "\". ",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private static void open(URI uri) {
		if (Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(uri);
			} catch (IOException e) {
				JOptionPane
						.showMessageDialog(
								null,
								"Failed to launch the link, your computer is likely misconfigured.",
								"Cannot Launch Link",
								JOptionPane.WARNING_MESSAGE);
				System.out.println(e.toString());
				System.out.println(uri.toString());
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Java is not able to launch links on your computer.",
					"Cannot Launch Link", JOptionPane.WARNING_MESSAGE);
		}
	}
}
