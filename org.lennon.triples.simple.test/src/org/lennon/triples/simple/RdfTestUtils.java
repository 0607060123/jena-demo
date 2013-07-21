package org.lennon.triples.simple;

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
}
