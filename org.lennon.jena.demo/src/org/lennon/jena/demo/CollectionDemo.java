package org.lennon.jena.demo;

import java.io.File;
import java.io.FileInputStream;

import com.hp.hpl.jena.rdf.model.Bag;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Selector;
import com.hp.hpl.jena.rdf.model.SimpleSelector;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

/**
 * Jena tutorial #10
 */
public class CollectionDemo {

	public static void main(String[] args) throws Exception {
		Model m1 = ModelFactory.createDefaultModel();
		Model m2 = ModelFactory.createDefaultModel();
		m1.read(new FileInputStream(new File("data/vc-db-1.rdf")), null);
		m2.read(new FileInputStream(new File("data/vc-db-2.rdf")), null);

		Model m3 = m1.union(m2);

		Selector sel = new SimpleSelector(null, VCARD.FN, (String)null) {
			@Override
			public boolean selects(Statement stmt) {
				return(stmt.getObject().asLiteral().getString().endsWith(" Bulger"));
			}
		};
		Bag bulgerBag = m3.createBag();
		StmtIterator iter = m3.listStatements(sel);
		while (iter.hasNext()) {
			Statement stmt = iter.nextStatement();
			bulgerBag.add(stmt.getSubject());
		}
		m3.write(System.out);
	}
}
