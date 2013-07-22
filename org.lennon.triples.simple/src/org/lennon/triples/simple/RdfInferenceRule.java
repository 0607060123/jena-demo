package org.lennon.triples.simple;

import java.util.List;

public abstract class RdfInferenceRule {

	public abstract List<RdfTriple> query(RdfGraph graph);

	public abstract List<RdfTriple> makeTriples(RdfTriple t);
}
