package org.lennon.triples.simple;

public class RdfTriple {

	private final RdfEntity s;
	private final String p;
	private final Object o;

	public RdfTriple(RdfEntity s, String p, Object o) {
		this.s = s;
		this.p = p;
		this.o = o;
	}

	public RdfEntity getSubject() {
		return s;
	}

	public Object getObject() {
		return o;
	}

	public String getPredicate() {
		return p;
	}
}
