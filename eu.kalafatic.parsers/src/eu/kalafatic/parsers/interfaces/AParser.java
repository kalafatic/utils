package eu.kalafatic.parsers.interfaces;

import static eu.kalafatic.parsers.lib.constants.FTextConstants.ATTRIBUTES;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.CHILDREN;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.ELEMENTS;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.KEY;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.VALUE;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public abstract class AParser implements IParser {

	protected File file;

	protected Map<String, Object> project = new LinkedHashMap<String, Object>();
	protected Map<String, Object> attributes = new LinkedHashMap<String, Object>();

	protected List<Map> children = new ArrayList<Map>();
	protected List<Object> elements = new ArrayList<Object>();

	public AParser(File file) {
		this.file = file;
		project.put(KEY, "MANIFEST");
		project.put(VALUE, file);

		project.put(ATTRIBUTES, attributes);
		project.put(CHILDREN, children);
		project.put(ELEMENTS, elements);
	}

	@Override
	public abstract Map parse() throws Exception;

	@Override
	public abstract void serialize(Map map) throws Exception;
}
