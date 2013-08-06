package org.lennon.jena.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * Exercise 7 in Jena tutorial
 */
public class SimpleSelectorDemo {

	/**
	 * Read sample vCard RDF DB.
	 * Print resources with full names that end with "Smith".
	 */
	public static void main(String[] args) throws Exception {
		Model m = ModelFactory.createDefaultModel();
		InputStream rin = new FileInputStream(new File("data/vc-db-1.rdf"));
		m.read(rin, null);
		String nullStr = null;
		SimpleSelector select = new SimpleSelector(null, VCARD.FN, nullStr) {
			@Override
			public boolean selects(Statement stmt) {
				return(stmt.getObject().asLiteral().getString().endsWith(" Smith"));
			}
		};
		StmtIterator iter = m.listStatements(select);
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement();
			System.out.println(stmt.getObject().asLiteral().getString());
		}
	}
}
