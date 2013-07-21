package org.lennon.triples.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RdfTestUtils {

	static RdfGraph createGraph() {
		return new RdfGraph();
	}

	static Date stringToDate(String dateStr) {
		try {
			return new SimpleDateFormat("MMMM dd, yyyy").parse(dateStr);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e);
		}
	}

	static String readStream(InputStream in, String encoding) throws IOException {
		Reader reader = new InputStreamReader(in, encoding);
		StringBuilder sb = new StringBuilder();
		char[] buffer = new char[256];
		for (;;) {
			int n = reader.read(buffer);
			if (n < 0) {
				break;
			}
			sb.append(buffer, 0, n);
		}
		return sb.toString();
	}

	/**
	 * Perform a GET on the specified URL and return the resulting
	 * response as a String.
	 * @throws IOException
	 */
	public static String readUrl(URL url) throws IOException {
		URLConnection con = url.openConnection();
		InputStream in = con.getInputStream();
		String encoding = con.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		return readStream(in, encoding);
	}
}
