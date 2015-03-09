package main;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;


public class HelloRDFWorld {

	public static void main(String[] args) {
		
		final String personURI = "http://somewhere/RonaldTeo";
		final String fullName = "Ronald Teo";
		final String givenName = "Ronald";
		final String familyName = "Teo";

		Model model = ModelFactory.createDefaultModel();
		
		Resource ronaldTeo = model.createResource(personURI)
									.addProperty(VCARD.FN, fullName)
									.addProperty(VCARD.N, model.createResource()
											.addProperty(VCARD.Given, givenName)
											.addProperty(VCARD.Family, familyName));
		
		model.write(System.out, "JSON-LD");
	}

}
