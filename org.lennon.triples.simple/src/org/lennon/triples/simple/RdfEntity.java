package org.lennon.triples.simple;

public class RdfEntity {

	private final String id;

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
