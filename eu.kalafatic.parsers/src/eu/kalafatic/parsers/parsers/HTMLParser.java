package eu.kalafatic.parsers.parsers;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

public class HTMLParser extends HTMLEditorKit.ParserCallback {
	@Override
	public void handleText(char[] data, int pos) {
		System.out.println(new String(data));
	}

	@Override
	public void handleStartTag(HTML.Tag tag, MutableAttributeSet att, int pos) {
		System.out.println("Start tag: " + tag);
	}

	@Override
	public void handleEndTag(HTML.Tag tag, int pos) {
		System.out.println("End tag: " + tag);
	}

	@Override
	public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet att, int pos) {
		if (tag == HTML.Tag.META) {
			String name1 = (String) att.getAttribute(HTML.Attribute.NAME);
			if (name1 != null) {
				System.out.println("META name1: " + name1);
			}
			String content1 = (String) att.getAttribute(HTML.Attribute.CONTENT);
			if (content1 != null) {
				System.out.println("META content1: " + content1);
			}
		}
	}

	// public static void main(String argv[]) {
	// try {
	// Reader read = new FileReader("files/1.html");
	// ParserDelegator parser = new ParserDelegator();
	// HTMLEditorKit.ParserCallback callback = new HTMLParser();
	// parser.parse(read, callback, false);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
