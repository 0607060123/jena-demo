package org.lennon.triples.simple;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PlaceTests {

	public class PlacesGraph extends RdfGraph {

		/**
		 * We know that the object in an "inside" triple should itself
		 * be an RdfEntity, not just a String. Make it so.
		 */
		@Override
		public void add(RdfTriple t) {
			if (t.getPredicate().equals(INSIDE_PREDICATE)) {
				RdfEntity o = new RdfEntity((String) t.getObject());
				super.add(new RdfTriple(t.getSubject(), INSIDE_PREDICATE, o));
			}
		}
	}

	private static final String PLACES_FILE = "data/place_triples.txt";
	private static final String NEW_HAMPSHIRE_ID = "New_Hampshire";
	private static final String INSIDE_PREDICATE = "inside";
	private RdfGraph placesGraph;

	@Before
	public void setup() throws Exception {
		placesGraph = new PlacesGraph();
		placesGraph.loadFromCsv(PLACES_FILE);
	}

	@Test
	public void testFindNewHampshirePlaces() {
		RdfEntity newHampshire = new RdfEntity(NEW_HAMPSHIRE_ID);
		List<RdfTriple> places = placesGraph.queryByTriple(null, INSIDE_PREDICATE, newHampshire);
		for (RdfTriple place : places) {
			System.out.println(place);
			assertEquals(place.getPredicate(), INSIDE_PREDICATE);
			assertEquals(place.getObject(), newHampshire);
		}
	}
}
