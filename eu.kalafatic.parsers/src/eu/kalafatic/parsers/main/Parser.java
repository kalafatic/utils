package eu.kalafatic.parsers.main;

import static eu.kalafatic.parsers.lib.constants.FConstants.ASSOC;

import java.io.File;

import eu.kalafatic.parsers.interfaces.IParser;
import eu.kalafatic.parsers.parsers.CustomParser;
import eu.kalafatic.parsers.parsers.TorrentParser;
import eu.kalafatic.parsers.parsers.XMLParser;
import eu.kalafatic.parsers.parsers.YAMLParser;

public class Parser {

	public static IParser getInstance(File file) {
		String family = getFamily(file);
		return getInstance(file, family);
	}

	/**
	 * @param file
	 * @param family
	 * @return
	 */
	private static IParser getInstance(File file, String family) {
		if (family.equals(Family.XML)) {
			return new XMLParser(file);
		} else if (family.equals(Family.TORRENT)) {
			return new TorrentParser(file);
		} else if (family.equals(Family.YAML)) {
			return new YAMLParser(file);
		} else {
			return new CustomParser(file);
		}
	}

	/**
	 * @param file
	 * @return
	 */
	private static String getFamily(File file) {
		String ext = file.getName().split("\\.")[1];
		return ASSOC.get(ext.toUpperCase());
	}

	public static final class Family {
		// data structure format languages
		public static final String XML = "XML";
		public static final String YAML = "YAML";
		public static final String TORRENT = "TORRENT";
		public static final String SPECIFIC = "SPECIFIC";
	}
}
