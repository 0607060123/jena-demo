package org.lennon.jena.demo;

import java.io.File;
import java.io.FileInputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ModelUnionDemo {

	public static void main(String[] args) throws Exception {
		Model m1 = ModelFactory.createDefaultModel();
		Model m2 = ModelFactory.createDefaultModel();
		m1.read(new FileInputStream(new File("data/vc-db-1.rdf")), null);
		m2.read(new FileInputStream(new File("data/vc-db-2.rdf")), null);

		Model m3 = m1.union(m2);
		m3.write(System.out);
	}
}
