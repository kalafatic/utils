package eu.kalafatic.parsers.interfaces;

import java.util.Map;

@SuppressWarnings("rawtypes")
public interface IParser {

	Map parse() throws Exception;

	void serialize(Map map) throws Exception;
}
