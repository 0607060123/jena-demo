package org.lennon.triples.simple;

import java.util.ArrayList;
import java.util.List;

public class RdfGraph {

	private List<RdfTriple> triples = new ArrayList<>();

	public void add(RdfTriple t) {
		// TODO Auto-generated method stub
	}

	public boolean contains(RdfTriple t) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<RdfTriple> queryByPredicate(String pred) {
		List<RdfTriple> results = new ArrayList();
		for (RdfTriple triple : triples) {
			if (triple.getPredicate().equals(pred)) {
				results.add(triple);
			}
		}
		return results;
	}
}
