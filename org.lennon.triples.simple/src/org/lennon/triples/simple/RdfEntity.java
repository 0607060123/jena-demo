package org.lennon.triples.simple;

/**
 * An entity in an RDF graph.
 */
public class RdfEntity {

	private final String id;

	/**
	 * Instance an entity with the specified unique id.
	 * @param id this entity's id; must be unique within the
	 * containing graph.
	 */
	public RdfEntity(String id) {
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof RdfEntity) {
			return id.equals(((RdfEntity) o).id);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return id;
	}
}
