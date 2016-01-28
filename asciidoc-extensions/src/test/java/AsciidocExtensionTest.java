import static org.asciidoctor.Asciidoctor.Factory.create;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.extension.JavaExtensionRegistry;

public class AsciidocExtensionTest {

	public static void main(String[] args) throws IOException {
		Asciidoctor asciidoctor = create();
		JavaExtensionRegistry extensionRegistry = asciidoctor.javaExtensionRegistry();

		extensionRegistry.treeprocessor( "org.beanvalidation.asciidocextensions.TestableRoleExtractor" );
		String testInput = readTestingInput();
		String content = asciidoctor.convert( testInput, new Options() );
		System.out.println( content );
	}

	private static String readTestingInput() throws IOException {
		try ( InputStream testInputStream = AsciidocExtensionTest.class.getClassLoader().getResourceAsStream( "SampleSourceDocument.asciidoc" ) ) {
			java.util.Scanner s = new java.util.Scanner( testInputStream ).useDelimiter( "\\A" );
			return s.hasNext() ? s.next() : "";
		}
	}

}
