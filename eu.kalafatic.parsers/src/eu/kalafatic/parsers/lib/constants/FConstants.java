package eu.kalafatic.parsers.lib.constants;

import java.util.HashMap;
import java.util.Map;

import eu.kalafatic.parsers.main.Parser.Family;

public final class FConstants {

	public static final Map<String, String> ASSOC = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put(Family.XML, Family.XML);
			put("XSD", Family.XML);
			put("JNLP", Family.XML);
			put("ECORE", Family.XML);
			put("GENMODEL", Family.XML);

			put("JSON", Family.YAML);

			put("TORRENT", Family.TORRENT);
		}
	};
}
