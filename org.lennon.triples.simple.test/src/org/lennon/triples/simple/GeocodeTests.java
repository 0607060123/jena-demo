package org.lennon.triples.simple;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GeocodeTests {
	
	static final String COORDINATES_PREDICATE = "coordinates";

	private static class GeocodedPlacesGraph extends RdfGraph {
		private static final String GEOCODER_URL_BASE = "http://rpc.geocoder.us/service/csv?address=";

		/**
		 * Use the free web-based geocoding service to find the
		 * latitude & longitude of each place triplet with an "address" predicate.
		 */
		@Override
		public void add(RdfTriple t) {
			if (t.getPredicate().equals(ADDRESS_PREDICATE)) {
				String addr = (String) t.getObject();
				try {
					String coords = getCoordinates(addr);
					// Add initial triple, plus geographic coordinates
					super.add(t);
					super.add(new RdfTriple(t.getSubject(), COORDINATES_PREDICATE, coords));
				} catch (IOException e) {
					System.err.format("Can't get coordinates for \"%s\": %s\n", addr, e.getMessage());
				}
			}
		}

		private static String getCoordinates(String addr) throws IOException {
			// Convert spaces in address to "+", append it to the geocoder URL,
			// and do a GET on the URL.
			URL url = new URL(GEOCODER_URL_BASE + addr.replace(' ', '+'));
			String latLongStr = RdfTestUtils.readUrl(url);
			// Result is a comma-separated string. The first two values
			// are the latitude and longitude.
			String[] values = latLongStr.split(",");
			return(values[0] + "," + values[1]);
		}
	}

	private static final String PLACES_FILE = "data/DC_addresses.txt";
	private static final String ADDRESS_PREDICATE = "address";
	private RdfGraph placesGraph;

	@Before
	public void setup() throws Exception {
		placesGraph = new GeocodedPlacesGraph();
		placesGraph.loadFromCsv(PLACES_FILE);
	}

	@Test
	public void testFindGeotaggedPlaces() {
		List<RdfTriple> places = placesGraph.queryByTriple(null, COORDINATES_PREDICATE, null);
		for (RdfTriple place : places) {
			System.out.println(place);
		}
	}
}
