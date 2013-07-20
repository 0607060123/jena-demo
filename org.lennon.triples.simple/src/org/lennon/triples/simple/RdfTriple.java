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

	@Override
	public boolean equals(Object o) {
		if (o instanceof RdfTriple) {
			RdfTriple other = (RdfTriple) o;
			return(s.equals(other.s) &&
				   p.equals(other.p) &&
				   o.equals(other.o));
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + s.hashCode();
		result = 31 * result + p.hashCode();
		result = 31 * result + o.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s", s, p, o);
	}
}
