package eu.kalafatic.parsers.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			throw new IOException("File is too large " + file.getName());
		}
		byte[] bytes = new byte[(int) length];

		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		is.close();
		return bytes;
	}

	public static void writeFile(String fileName, byte[] content)
			throws IOException {
		writeFile(new File(fileName), content);
	}

	public static void writeFile(File file, byte[] content) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(content);
		fos.close();
	}

}
