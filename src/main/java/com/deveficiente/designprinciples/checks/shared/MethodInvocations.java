package com.deveficiente.designprinciples.checks.shared;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.sonar.plugins.java.api.tree.BlockTree;
import org.sonar.plugins.java.api.tree.ExpressionStatementTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.StatementTree;

/**
 * Just a wrapper for BlockTree. 
 * @author Alberto
 *
 */
public class MethodInvocations  {

	private List<StatementTree> body = new ArrayList<>();

	public MethodInvocations(BlockTree blockTree) {
		this.body.addAll(blockTree.body());
	}

	/**
	 * 
	 * @return all method invocation of a given block of code
	 */
	public Stream<MethodInvocationTree> all() {
		return body.stream()
		.filter(l -> l instanceof ExpressionStatementTree)
		.map(l -> ((ExpressionStatementTree) l).expression())
		.filter(ex -> ex instanceof MethodInvocationTree)
		.map(ex -> (MethodInvocationTree)ex);
	}

}
