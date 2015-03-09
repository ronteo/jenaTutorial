import javax.swing.JTable.ModelChange;

import spock.lang.Shared;
import spock.lang.Specification

import com.hp.hpl.jena.rdf.model.Model
import com.hp.hpl.jena.rdf.model.ModelFactory

import org.custommonkey.xmlunit.*

class RDFTutorial extends Specification {

	Model model = ModelFactory.createDefaultModel();
	
	/*@Shared*/
	final String rdfString ='''<rdf:RDF
					xmlns:rdf='http://www.w3.org/1999/02/22-rdf-syntax-ns#'
					xmlns:vcard='http://www.w3.org/2001/vcard-rdf/3.0#' >
						<rdf:Description rdf:nodeID="A0">
							<vcard:Family>Smith</vcard:Family>
							<vcard:Given>John</vcard:Given>
						</rdf:Description>
						<rdf:Description rdf:about='http://somewhere/JohnSmith/'>
		    				<vcard:FN>John Smith</vcard:FN>
		    				<vcard:N rdf:nodeID="A0"/>
						</rdf:Description>
					</rdf:RDF>
			'''


	def 'Test reading RDF into model'() {
		
		def outStream;
		
		given: 'Model read from rdfString'
			model.read(new ByteArrayInputStream(rdfString.getBytes()), null)
			outStream = new ByteArrayOutputStream()
			
		and: 'Ignore whitespaces when comparing expected and actual result'
			XMLUnit.ignoreWhitespace = true
			
		when: 'Model is written to outputstream'
			
			model.write(outStream)
		
		then: 'The xml produced should be similar'
			 def difference = new Diff(outStream.toString() , rdfString)
			 difference.similar()
	}
}
