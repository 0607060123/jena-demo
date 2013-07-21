package org.lennon.triples.simple;

/**
 * RDF triple: subject, predicate, object.
 */
public class RdfTriple {

	private final RdfEntity s;
	private final String p;
	private final Object o;
	private int hash;

	/**
	 * Instantiate new triple.
	 * @param s subject
	 * @param p predicate
	 * @param o object; may be any type (String, Date, etc.).
	 *  Can be type RdfEntity, in which case 
	 */
	public RdfTriple(RdfEntity s, String p, Object o) {
		this.s = s;
		this.p = p;
		this.o = o;
	}

	/**
	 * Get this triple's subject.
	 */
	public RdfEntity getSubject() {
		return s;
	}

	/**
	 * Get this triple's predicate.
	 */
	public String getPredicate() {
		return p;
	}

	/**
	 * Get this triple's object.
	 */
	public Object getObject() {
		return o;
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
		if (hash == 0) {
			hash = 17;
			hash = 31 * hash + s.hashCode();
			hash = 31 * hash + p.hashCode();
			hash = 31 * hash + o.hashCode();
		}
		return hash;
	}

	@Override
	public String toString() {
		return String.format("%s,%s,%s", s, p, o);
	}
}
