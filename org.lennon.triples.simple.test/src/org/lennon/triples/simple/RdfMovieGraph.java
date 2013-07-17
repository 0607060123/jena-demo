package org.lennon.triples.simple;


public class RdfMovieGraph extends RdfGraph {

	static final String BLADE_RUNNER_TITLE = "Blade Runner";
	static final String TITLE_PREDICATE = "title";
	static final String DIRECTED_BY_PREDICATE = "directed-by";
	private static final String RIDLEY_SCOTT_ID = "ridley-scott";
	private static final String JOHN_HUSTON_ID = "john-huston";
	private static final String BLADE_RUNNER_ID = "blade-runner";
	private static final String AFRICAN_QUEEN_ID = "african-queen";
	private static final String ALIEN_ID = "alien";

	static private RdfMovieGraph instance;

	private final RdfEntity bladeRunner;
	private final RdfEntity africanQueen;
	private final RdfEntity alien;
	private final RdfEntity ridleyScott;
	private final RdfEntity johnHuston;

	static RdfMovieGraph getInstance() {
		if (instance == null) {
			instance = new RdfMovieGraph();
		}
		return instance;
	}

	private RdfMovieGraph() {
		bladeRunner = new RdfEntity(BLADE_RUNNER_ID);
		alien = new RdfEntity(ALIEN_ID);
		africanQueen = new RdfEntity(AFRICAN_QUEEN_ID);
		
		ridleyScott = new RdfEntity(RIDLEY_SCOTT_ID);
		johnHuston = new RdfEntity(JOHN_HUSTON_ID);

		add(new RdfTriple(bladeRunner, TITLE_PREDICATE, BLADE_RUNNER_TITLE));
		add(new RdfTriple(bladeRunner, DIRECTED_BY_PREDICATE, ridleyScott));
		
		add(new RdfTriple(africanQueen, TITLE_PREDICATE, AFRICAN_QUEEN_ID));
		add(new RdfTriple(africanQueen, DIRECTED_BY_PREDICATE, johnHuston));
		
		add(new RdfTriple(alien, TITLE_PREDICATE, ALIEN_ID));
		add(new RdfTriple(alien, DIRECTED_BY_PREDICATE, ridleyScott));

		// Question: "directed by" and "directed" are logically redundant.
		// Do we have to represent them explicitly? I guess so - I suspect
		// that later we'll find a way to encode the fact that certain
		// types of triples are commutative.
	}


	RdfEntity getBladeRunnerMovie() {
		return bladeRunner;
	}

	public Object getRidleyScott() {
		return ridleyScott;
	}
}
