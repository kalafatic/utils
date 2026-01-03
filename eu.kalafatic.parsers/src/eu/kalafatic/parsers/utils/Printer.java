package eu.kalafatic.parsers.utils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Printer {

	private static String level = "", leaf = "'-";

	@SuppressWarnings("unchecked")
	public static void print(Object object) {
		if (object instanceof Map) {
			print((Map<String, Object>) object);

		} else if (object instanceof List) {
			print((List<Object>) object);

		} else if (object instanceof Entry) {
			Entry<String, Object> entry = (Entry<String, Object>) object;
			try {
				if (entry.getValue() instanceof byte[]) {
					print(entry.getKey(), new String((byte[]) entry.getValue()));
				} else if (entry.getValue() instanceof String) {
					print(entry.getKey(), (String) entry.getValue());
				} else {
					String tab = setIndent(entry.getKey());
					System.err.println(tab + "  |" + entry.getKey());

					print(entry.getValue());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void print(Map<String, Object> map) {
		level += "    ";
		Iterator<Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			print(entry);
		}
	}

	private static void print(List<Object> list) {
		for (Object object : list) {
			print(object);
		}
	}

	private static void print(String key, String value) {
		String tab = setIndent(key + "\t");
		System.err.println(tab + leaf + key + "=" + value);
	}

	private static String setIndent(String tab) {
		int length = tab.length();
		String substring = tab.substring(0, length / 2 == 0 ? length
				: length / 2);
		return level + substring.replaceAll("[a-zA-Z'-]", " ");
	}
}
