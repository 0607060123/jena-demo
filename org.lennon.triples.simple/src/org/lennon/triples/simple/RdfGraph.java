package org.lennon.triples.simple;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RdfGraph {

	private Set<RdfTriple> triples = new HashSet<RdfTriple>();

	/**
	 * Add the specified triple to this graph.
	 * Duplicates are automatically eliminated.
	 */
	public void add(RdfTriple t) {
		triples.add(t);
	}

	/**
	 * Is the specified triple in this graph?
	 */
	public boolean contains(RdfTriple t) {
		return triples.contains(t);
	}

	public List<RdfTriple> queryByTriple(RdfEntity s, String p, Object o) {
		List<RdfTriple> results = new ArrayList<RdfTriple>();
		for (RdfTriple triple : triples) {
			if (matches(triple, s, p, o)) {
				results.add(triple);
			}
		}
		return results;
	}

	private static boolean matches(RdfTriple t, RdfEntity s, String p, Object o) {
		// If query subject is not null, must match the triple's subject
		if (s != null && ! t.getSubject().equals(s)) {
			return false;
		}
		if (p != null && ! t.getPredicate().equals(p)) {
			return false;
		}
		if (o != null && ! t.getObject().equals(o)) {
			return false;
		}
		return true;
	}

	/**
	 * Add the triple data from the specified CSV file to the existing
	 * triples in this graph instance.
	 * @param csvFile file containing triples, one per line.
	 * @throws IOException if CSV file does not exist, is not well formed,
	 *         cannot be read, etc.
	 */
	public void loadFromCsv(String csvFile) throws IOException {
		try (BufferedReader csvIn = new BufferedReader(new FileReader(csvFile))) {
			String line;
			while ((line = csvIn.readLine()) != null) {
				String[] spo = line.split(",", 3);
				if (spo.length != 3) {
					System.err.println("Skipping badly formatted line: " + line);
					continue;
				}
				add(new RdfTriple(new RdfEntity(spo[0]), spo[1], spo[2]));
			}
		}
	}

	public void dumpTriples() {
		for (RdfTriple triple : triples) {
			System.out.println(triple);
		}
	}

	public void applyInferenceRule(RdfInferenceRule rule) {
		for (RdfTriple t : rule.query(this)) {
			for (RdfTriple x : rule.makeTriples(t)) {
				add(x);
			}
		}
	}
}
