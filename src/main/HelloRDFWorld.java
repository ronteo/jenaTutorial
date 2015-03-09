package main;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;


public class HelloRDFWorld {

	public static void main(String[] args) {
		
		final String personURI = "http://somewhere/RonaldTeo";
		final String givenName = "Ronald";
		final String familyName = "Teo";
		final String fullName = givenName + " " + familyName;

		Model model = ModelFactory.createDefaultModel();
		
		Resource ronaldTeo = model.createResource(personURI)
									.addProperty(VCARD.FN, fullName)
									.addProperty(VCARD.N, model.createResource()
									.addProperty(VCARD.Given, givenName)
									.addProperty(VCARD.Family, familyName));
		

		StmtIterator iter = model.listStatements();
		
		while(iter.hasNext()) {
			Statement stmt = iter.nextStatement();
			
			Resource subject = stmt.getSubject();
			Property predicate = stmt.getPredicate();
			RDFNode object = stmt.getObject();
			
			System.out.print(subject.toString());
			System.out.print(" " + predicate.toString() + " ");
			
		    if (object instanceof Resource) {
		        System.out.print(object.toString());
		     } else {
		         // object is a literal
		         System.out.print(" \"" + object.toString() + "\"");
		     }
		    
		    System.out.println(" .");
		}
		
	}
	
	

}
