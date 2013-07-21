package org.lennon.triples.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MovieTests {

	private RdfMovieGraph movieGraph;
	private RdfEntity bladeRunner;
	private Object ridleyScott;

	@Before
	public void setup() {
		movieGraph = RdfMovieGraph.getInstance();
		bladeRunner = movieGraph.getBladeRunnerMovie();
		ridleyScott = movieGraph.getRidleyScott();
	}

	@Test
	public void testFindBladeRunnerByTitle() {
		String title = RdfMovieGraph.BLADE_RUNNER_TITLE;
		List<RdfTriple> movies = movieGraph.queryByTriple(null, RdfMovieGraph.TITLE_PREDICATE, RdfMovieGraph.BLADE_RUNNER_TITLE);
		for (RdfTriple movie : movies) {
			if (movie.getObject().equals(title)) {
				assertEquals(movie.getSubject(), bladeRunner);
			}
		}
		fail("Could not find Blade Runner");
	}

	@Test
	public void testDirectedByRidleyScott() {
		List<RdfTriple> movies = movieGraph.queryByTriple(null, RdfMovieGraph.DIRECTED_BY_PREDICATE, ridleyScott);
		boolean foundBladeRunner = false;
		for (RdfTriple movie : movies) {
			assertTrue(movie.getObject().equals(ridleyScott));
			if (movie.getSubject().equals(bladeRunner)) {
				foundBladeRunner = true;
			}
		}
		assertTrue("Could not find Blade Runner", foundBladeRunner);
	}
}
