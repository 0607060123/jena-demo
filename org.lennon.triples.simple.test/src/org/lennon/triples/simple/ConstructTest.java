package org.lennon.triples.simple;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ConstructTest {

	private RdfTriple testTriple;
	private RdfGraph testGraph;

	@Before
	public void setup() {
		testGraph = RdfTestUtils.createGraph();
		testTriple = RdfTestUtils.createRdfTriple();
	}

	@Test
	public void test() {
		RdfGraph store = new RdfGraph();
	}

	@Test
	public void testAddTriple() {
		testGraph.add(testTriple);
		assertTrue(testGraph.contains(testTriple));
	}
}
