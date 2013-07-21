package org.lennon.triples.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class QueryTests {

	private static final String TITLE_PREDICATE = "title";
	private static final String BLADE_RUNNER_TITLE = "Blade Runner";
	private static final String DIRECTED_BY_PREDICATE = "directed-by";
	private static final String BLADE_RUNNER_ID = "blade-runner";
	private static final String ALIEN_ID = "alien";
	private static final String AFRICAN_QUEEN_ID = "african-queen";
	private static final String RIDLEY_SCOTT_ID = "ridley-scott";
	private static final String JOHN_HUSTON_ID = "john-huston";
	private static final String RELEASE_DATE_PREDICATE = "release-date";

	private RdfGraph testGraph;
	private RdfEntity bladeRunner;
	private Object ridleyScott;
	private RdfEntity alien;
	private RdfEntity africanQueen;
	private RdfEntity johnHuston;

	@Before
	public void setup() {
		testGraph = new RdfGraph();

		bladeRunner = new RdfEntity(BLADE_RUNNER_ID);
		alien = new RdfEntity(ALIEN_ID);
		africanQueen = new RdfEntity(AFRICAN_QUEEN_ID);
		
		ridleyScott = new RdfEntity(RIDLEY_SCOTT_ID);
		johnHuston = new RdfEntity(JOHN_HUSTON_ID);

		testGraph.add(new RdfTriple(bladeRunner, TITLE_PREDICATE, BLADE_RUNNER_TITLE));
		testGraph.add(new RdfTriple(bladeRunner, DIRECTED_BY_PREDICATE, ridleyScott));
		testGraph.add(new RdfTriple(bladeRunner, RELEASE_DATE_PREDICATE, RdfTestUtils.stringToDate("June 25, 1972")));
		
		testGraph.add(new RdfTriple(africanQueen, TITLE_PREDICATE, AFRICAN_QUEEN_ID));
		testGraph.add(new RdfTriple(africanQueen, DIRECTED_BY_PREDICATE, johnHuston));
		testGraph.add(new RdfTriple(bladeRunner, RELEASE_DATE_PREDICATE, RdfTestUtils.stringToDate("February 20, 1952")));
		
		testGraph.add(new RdfTriple(alien, TITLE_PREDICATE, ALIEN_ID));
		testGraph.add(new RdfTriple(alien, DIRECTED_BY_PREDICATE, ridleyScott));
		testGraph.add(new RdfTriple(bladeRunner, RELEASE_DATE_PREDICATE, RdfTestUtils.stringToDate("June 22, 1979")));

	}

	@Test
	public void testFindBladeRunnerByTitle() {
		List<RdfTriple> movies = testGraph.queryByTriple(null, TITLE_PREDICATE, BLADE_RUNNER_TITLE);
		assertEquals(movies.size(), 1);
		RdfTriple movie = movies.get(0);
		assertEquals(movie.getSubject(), bladeRunner);
		assertEquals(movie.getPredicate(), TITLE_PREDICATE);
		assertEquals(movie.getObject(), BLADE_RUNNER_TITLE);

	}

	@Test
	public void testDirectedByRidleyScott() {
		List<RdfTriple> movies = testGraph.queryByTriple(null, DIRECTED_BY_PREDICATE, ridleyScott);
		assertTrue("Too many Ridley Scott Movies", movies.size()==2);
		for (RdfTriple movie : movies) {
			System.out.println(movie);
			assertEquals(movie.getPredicate(), DIRECTED_BY_PREDICATE);
			assertEquals(movie.getObject(), ridleyScott);
			assertTrue(movie.getSubject().equals(bladeRunner) ||
					   movie.getSubject().equals(alien));
		}
	}
}
