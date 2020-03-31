package com.deveficiente.designprinciples.checks.java.design;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.semantic.Symbol;
import org.sonar.plugins.java.api.tree.ExpressionStatementTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.StatementTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import com.deveficiente.designprinciples.checks.shared.MethodInvocations;

import org.sonar.plugins.java.api.tree.VariableTree;

@Rule(key = "DoNotChangeParameterReferencesRule", 
		name = "Do not change parameter reference state", 
		priority = Priority.BLOCKER, 
		tags = {"code-smell" })
public class DoNotChangeParameterReferencesRule extends IssuableSubscriptionVisitor {

	@Override
	public List<Kind> nodesToVisit() {
		return Arrays.asList(Tree.Kind.METHOD, Tree.Kind.CONSTRUCTOR);
	}

	@Override
	public void visitNode(Tree tree) {
		MethodTree methodTree = (MethodTree) tree;

		if (methodTree.block() == null || methodTree.parameters().isEmpty()) {
			return;
		}

		List<VariableTree> parameters = methodTree.parameters();

		Set<String> parametersNames = parameters.stream().map(VariableTree::symbol).map(Symbol::name)
				.collect(Collectors.toSet());

		MethodInvocations methodInvocations = new MethodInvocations(methodTree.block());

		methodInvocations.all()
				.filter(method -> wasInvokedByAParameterReference(method, parametersNames))
				.filter(this :: isAPossibleMutatingMethod)
				.forEach(m -> {
					context.reportIssue(this, m, "Do not change parameter state");
				});

	}

	private boolean isAPossibleMutatingMethod(MethodInvocationTree method) {
		return method.symbol().name().startsWith("set");
	}

	private boolean wasInvokedByAParameterReference(MethodInvocationTree method,Set<String> parametersNames) {
		return parametersNames.contains(method.firstToken().text());
	}

}
