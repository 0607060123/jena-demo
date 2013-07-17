package org.lennon.triples.simple;

public class RdfTestUtils {

	static RdfGraph createGraph() {
		return new RdfGraph();
	}

	static RdfTriple createRdfTriple() {
		RdfEntity s = new RdfEntity("S1");
		RdfEntity o = new RdfEntity("Blade Runner");
		return new RdfTriple(s, "directed", o);
	}

	static RdfGraph createMovieGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	static RdfEntity getBladeRunnerSubject() {
		// TODO Auto-generated method stub
		return null;
	}

	static String getBladeRunnerTitle() {
		// TODO Auto-generated method stub
		return null;
	}

}
