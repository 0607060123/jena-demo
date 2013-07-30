package org.lennon.jena.demo;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

public class MyCard {
	private static final String MATT_URI = "http://lennon.org/ns/matt_lennon";
	private static final String MATT_FULL_NAME = "Matt Lennon";

	public static void main(String[] argv) {
		Model model = ModelFactory.createDefaultModel();
		Resource matt = model.createResource(MATT_URI);
		matt.addProperty(VCARD.FN, MATT_FULL_NAME);
	}
}
