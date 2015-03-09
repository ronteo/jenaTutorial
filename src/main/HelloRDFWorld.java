package main;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;


public class HelloRDFWorld {

	public static void main(String[] args) {
		
		final String personURI = "http://somewhere/RonaldTeo";
		final String fullName = "Ronald Teo";

		Model model = ModelFactory.createDefaultModel();
		
		Resource ronaldTeo = model.createResource(personURI);
		ronaldTeo.addProperty(VCARD.FN, fullName);
		
		model.write(System.out, "JSON-LD");
	}

}
