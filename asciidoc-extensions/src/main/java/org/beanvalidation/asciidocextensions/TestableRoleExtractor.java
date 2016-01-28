package org.beanvalidation.asciidocextensions;

import java.util.Map;

import org.asciidoctor.ast.Document;
import org.asciidoctor.extension.Treeprocessor;

public class TestableRoleExtractor extends Treeprocessor {

	private Document document;

	public TestableRoleExtractor(Map<String, Object> config) {
		super(config);
	}

	@Override
	public Document process(Document document) {
		this.document = document;
		System.out.println(  "HEY HEY HEY" );
		return document;
	}

}
