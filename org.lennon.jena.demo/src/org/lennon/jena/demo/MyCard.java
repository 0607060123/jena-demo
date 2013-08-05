package org.lennon.jena.demo;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * Working on Jena tutorial:
 *     http://jena.apache.org/tutorials
 */
public class MyCard {
	private static final String MATT_URI = "http://lennon.org/ns/matt_lennon";
	private static final String MATT_FULL_NAME = "Matt Lennon";
	private static final String MATT_GIVEN_NAME = "Matthew";
	private static final String MATT_FAMILY_NAME = "Lennon";
	private static final String JMATTHEW_FULL_NAME = "James Matthew Lennon";

	public static void main(String[] argv) {
		Model model = ModelFactory.createDefaultModel();
		Resource matt = model.createResource(MATT_URI)
				.addProperty(VCARD.FN, MATT_FULL_NAME)
				.addProperty(VCARD.FN, JMATTHEW_FULL_NAME)
				.addProperty(VCARD.N, model.createResource()
						.addProperty(VCARD.Given, MATT_GIVEN_NAME)
						.addProperty(VCARD.Family, MATT_FAMILY_NAME));
		printResource(matt, "");
		System.out.println("===========");
		printModel(model);

		Resource matt2 = model.getResource(MATT_URI);
		assert(matt.equals(matt2));
		System.out.println(matt2.getPropertyResourceValue(VCARD.N).getProperty(VCARD.Given).getLiteral());
	}

	private static void printModel(Model m) {
		m.write(System.out);
	}

	private static void printResource(Resource r, String indent) {
		System.out.println(indent + r);
		indent = indent + "  ";
		for (StmtIterator iter = r.listProperties(); iter.hasNext(); ) {
			Statement stmt = iter.next();
			System.out.format("%s%s=%s\n", indent, stmt.getPredicate(), stmt.getObject());
			if (stmt.getObject() instanceof Resource) {
				printResource(stmt.getResource(), indent);
			} else {
				assert(stmt.getObject() instanceof Literal);
			}
		}
	}
}
