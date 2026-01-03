package eu.kalafatic.parsers.parsers;

import static eu.kalafatic.parsers.lib.constants.FTextConstants.ATTRIBUTES;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.CHILDREN;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.DATA;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.ELEMENTS;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.KEY;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.VALUE;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//import org.apache.xml.serialize.OutputFormat;
//import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eu.kalafatic.parsers.interfaces.AParser;
import eu.kalafatic.parsers.utils.Printer;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class XMLParser extends AParser {

	private Pattern pattern = Pattern.compile("[,\\s]+");

	public XMLParser(File file) {
		super(file);
	}

	@Override
	public Map parse() throws Exception {
		Document document = parse(file);
		return toMap(document);
	}

	@Override
	public void serialize(Map map) throws Exception {
//		Document document = (Document) project.get(DATA);
//		File file = (File) map.get(VALUE);
//
//		OutputFormat format = new OutputFormat(document);
//		format.setIndenting(true);
//		// to generate output to console use this serializer
//		// XMLSerializer serializer = new XMLSerializer(System.out, format);
//
//		XMLSerializer serializer = new XMLSerializer(
//				new FileOutputStream(file), format);
//		serializer.serialize(document);
	}

	private Document parse(File file) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setIgnoringComments(false);
		dbf.setIgnoringElementContentWhitespace(false);
		dbf.setExpandEntityReferences(false);

		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(file.getPath());
	}

	private Map<String, Object> toMap(Document document) {
		project.put(DATA, document);

		toMap(project, document.getDocumentElement());
		// Printer.print(project);
		return project;
	}

	private void toMap(Map<String, Object> map, Node node) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			// setup name,attributes,children
			setNode(map, node, null);
			// children,..
			toMap(map, node.getChildNodes());
		}
	}

	/**
	 * @param childMap
	 * @param childNodes
	 */
	private void toMap(Map<String, Object> map, NodeList childNodes) {
		List<Map> children = (List<Map>) map.get(CHILDREN);
		List<Object> elements = (List<Object>) map.get(ELEMENTS);

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node childNode = childNodes.item(i);
			Node child = childNode.getFirstChild();

			if (child != null) {
				String value = child.getNodeValue();

				if (child.getNodeType() == Node.TEXT_NODE && !isOK(value)) {
					Map<String, Object> childMap = new LinkedHashMap<String, Object>();
					setNode(childMap, childNode, value);
					elements.add(childMap);

				} else if (childNode.getNodeType() == Node.ELEMENT_NODE) {
					toMap(children, childNode);
				}
			} else if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				toMap(children, childNode);
			}
		}
	}

	private void toMap(List<Map> children, Node childNode) {
		Map<String, Object> childMap = new LinkedHashMap<String, Object>();
		children.add(childMap);
		toMap(childMap, childNode);
	}

	private void setNode(Map<String, Object> map, Node node, Object value) {
		if (map.get(KEY) == null) {
			map.put(KEY, node.getNodeName());
		}
		if (map.get(DATA) == null) {
			map.put(DATA, node);
		}
		if (map.get(CHILDREN) == null) {
			map.put(CHILDREN, new ArrayList<Map>());
		}

		if (value == null) {
			if (map.get(ELEMENTS) == null) {
				map.put(ELEMENTS, new ArrayList<Object>());
			}
		} else {
			map.put(VALUE, value);
		}

		Map attributes = (Map) map.get(ATTRIBUTES);
		if (attributes == null) {
			attributes = new LinkedHashMap<String, Object>();
			map.put(ATTRIBUTES, attributes);
		}

		NamedNodeMap attr = node.getAttributes();
		for (int i = 0; i < attr.getLength(); i++) {
			Node item = attr.item(i);
			attributes.put(item.getNodeName(), item.getNodeValue());
		}
	}

	private boolean isOK(String s) {
		return s == null || s.isEmpty() || pattern.matcher(s).find();
	}

	public static void main(String[] args) throws Exception {
		Map parse = new XMLParser(new File("c:/GE/maintain/project.xml"))
				.parse();
		Printer.print(parse);
		System.err.println(parse);
	}
}
