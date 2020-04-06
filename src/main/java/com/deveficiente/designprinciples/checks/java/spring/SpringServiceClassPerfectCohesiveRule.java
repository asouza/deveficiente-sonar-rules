package com.deveficiente.designprinciples.checks.java.spring;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;

import com.deveficiente.designprinciples.checks.shared.MethodInvocations;

@Rule(key = "SpringServiceClassPerfectCohesiveRule", name = "A service must be 100% cohesive", priority = Priority.BLOCKER, tags = {
		"code-smell" })
public class SpringServiceClassPerfectCohesiveRule extends BaseTreeVisitor implements JavaFileScanner {

	private JavaFileScannerContext context;

	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
		scan(context.getTree());
	}

	private Set<String> visitBlock(MethodTree tree) {
		Stream<MethodInvocationTree> methodInvocations = new MethodInvocations(tree.block()).all();

		return methodInvocations.map(invocation -> invocation.firstToken().text()).collect(Collectors.toSet());

	}

	@Override
	public void visitClass(ClassTree tree) {

		if (tree.symbol().metadata().isAnnotatedWith("org.springframework.stereotype.Service")) {
			Set<String> allClassVariables = new HashSet<>();

			// collecting variable names
			tree.members().stream().filter(member -> member.is(Kind.VARIABLE)).map(member -> (VariableTree) member)
					.map(variable -> variable.symbol().name()).forEach(allClassVariables::add);

			// verifying variable usage
			tree.members().stream().filter(member -> member.is(Kind.METHOD)).map(member -> (MethodTree) member)
					.flatMap(method -> this.visitBlock(method).stream()).forEach(allClassVariables::remove);
			
			if (!allClassVariables.isEmpty()) {
				context.reportIssue(this, tree.simpleName(), "Service should be 100% cohesive");
			}
		}

		super.visitClass(tree);
	}

}
