package org.lennon.triples.simple;

import static org.junit.Assert.*;

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
		List<RdfTriple> movies = movieGraph.queryByPredicate(RdfMovieGraph.TITLE_PREDICATE);
		for (RdfTriple movie : movies) {
			if (movie.getObject().equals(title)) {
				assertEquals(movie.getSubject(), bladeRunner);
			}
		}
		fail("Could not find Blade Runner");
	}

	@Test
	public void testDirectedByRidleyScott() {
		String directedBy = RdfMovieGraph.DIRECTED_BY_PREDICATE;
		List<RdfTriple> movies = movieGraph.queryByPredicate(RdfMovieGraph.DIRECTED_BY_PREDICATE);
		for (RdfTriple movie : movies) {
			if (movie.getObject().equals(ridleyScott)) {
				assertEquals(movie.getSubject(), bladeRunner);
			}
		}
		fail("Could not find Blade Runner");
	}
}
