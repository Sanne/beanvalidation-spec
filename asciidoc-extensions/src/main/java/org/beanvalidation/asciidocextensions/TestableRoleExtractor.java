package org.beanvalidation.asciidocextensions;

import java.util.List;
import java.util.Map;

import org.asciidoctor.ast.AbstractBlock;
import org.asciidoctor.ast.Block;
import org.asciidoctor.ast.Document;
import org.asciidoctor.ast.Section;
import org.asciidoctor.extension.Treeprocessor;

public class TestableRoleExtractor extends Treeprocessor {

	private Document document;

	public TestableRoleExtractor(Map<String, Object> config) {
		super(config);
	}

	@Override
	public Document process(Document document) {
		this.document = document;
		List<AbstractBlock> blocks = document.getBlocks();
		inspectChildren( 1, blocks );
		System.out.println(  "HEY HEY HEY" );
		return document;
	}

	private void inspectChildren(int depth, List<AbstractBlock> blocks) {
		for ( AbstractBlock block : blocks ) {
			indent( depth ); System.out.println( "depth: " + depth );
			indent( depth ); System.out.println( "nodeName: " + block.getNodeName() );
			indent( depth ); System.out.println( "role: " + block.role() );
			indent( depth ); System.out.println( "context: " + block.getContext() );
			indent( depth ); System.out.println( "markers: " + block.listMarkerKeyword() );
			indent( depth ); System.out.println( "roles: " + block.getRoles() );
//			indent( depth ); System.out.println( "content: " + block.getContent() );
//			indent( depth ); System.out.println( "lines: " + b.lines() );
			inspectAsSection( depth, block );
			inspectAsBlock( depth, block );
			inspectChildren( depth + 1, block.blocks() );
		}
	}

	private void inspectAsBlock(int depth, AbstractBlock block) {
		if ( block instanceof Block ) {
			Block b = (Block) block;
			List<String> lines = b.lines();
			for ( String line : lines ) {
				indent( depth ); System.out.println( "##| " + line );
			}
		}
	}

	private void inspectAsSection(int depth, AbstractBlock block) {
		if ( block instanceof Section ) {
			Section s = (Section) block;
			indent( depth ); System.out.println( "section id: " + s.id() );
			indent( depth ); System.out.println( "section number: " + s.numbered() );
		}
	}

	private void indent(int depth) {
		for (int i=0;i<depth;i++) System.out.print("\t");
	}

}
