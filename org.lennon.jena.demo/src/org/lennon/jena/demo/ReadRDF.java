package org.lennon.jena.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ReadRDF {

	public static void main(String[] args) throws Exception {
		Model m = ModelFactory.createDefaultModel();
		InputStream rin = new FileInputStream(new File("vc-db-1.rdf"));
		m.read(rin, null);
		m.write(System.out);
	}
}
