package eu.kalafatic.parsers.parsers;

import static eu.kalafatic.parsers.lib.constants.FTextConstants.MAP;
import static eu.kalafatic.parsers.lib.constants.FTextConstants.VALUE;

import java.io.File;
import java.util.Map;

import eu.kalafatic.parsers.decoders.BDecoder;
import eu.kalafatic.parsers.decoders.BEncoder;
import eu.kalafatic.parsers.interfaces.AParser;
import eu.kalafatic.parsers.utils.FileUtils;
import eu.kalafatic.parsers.utils.Printer;

@SuppressWarnings({ "rawtypes" })
public class TorrentParser extends AParser {

	public TorrentParser(File file) {
		super(file);
	}

	@Override
	public Map parse() throws Exception {
		byte[] bytesFromFile = FileUtils.getBytesFromFile(file);
		Map<?, ?> decoded = BDecoder.decode(bytesFromFile);
		project.put(MAP, decoded);
		Printer.print(decoded);
		return project;
	}

	@Override
	public void serialize(Map map) throws Exception {
		byte[] encoded = BEncoder.encode((Map<?, ?>) map.get(MAP));
		FileUtils.writeFile((File) map.get(VALUE), encoded);
	}
}
