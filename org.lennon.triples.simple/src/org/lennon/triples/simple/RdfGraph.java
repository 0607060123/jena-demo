package org.lennon.triples.simple;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RdfGraph {

	private Set<RdfTriple> triples = new HashSet<RdfTriple>();

	public void add(RdfTriple t) {
		triples.add(t);
	}

	public boolean contains(RdfTriple t) {
		return triples.contains(t);
	}

	public List<RdfTriple> queryByPredicate(String pred) {
		List<RdfTriple> results = new ArrayList<RdfTriple>();
		for (RdfTriple triple : triples) {
			if (triple.getPredicate().equals(pred)) {
				results.add(triple);
			}
		}
		return results;
	}

	public static RdfGraph loadFromCsv(String testFile) throws IOException {
		RdfGraph graph = new RdfGraph();
		try (BufferedReader csvIn = new BufferedReader(new FileReader(testFile))) {
			String line;
			while ((line = csvIn.readLine()) != null) {
				String[] spo = line.split(",", 3);
				if (spo.length != 3) {
					System.err.println("Skipping badly formatted line: " + line);
					continue;
				}
				graph.add(new RdfTriple(new RdfEntity(spo[0]), spo[1], spo[2]));
			}
			return graph;
		}
	}

	public void dumpTriples() {
		for (RdfTriple triple : triples) {
			System.out.println(triple);
		}
	}
}
