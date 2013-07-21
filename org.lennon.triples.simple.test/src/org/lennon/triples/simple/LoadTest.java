package org.lennon.triples.simple;

import org.junit.Test;

public class LoadTest {

	private static String testFile = "data/movies.csv";

	@Test
	public void testLoadCsvStream() throws Exception {
		RdfGraph graph = new RdfGraph();
		graph.loadFromCsv(testFile);
		graph.dumpTriples();
	}
}
