package com.deveficiente.designprinciples.checks.java.design;

import java.util.stream.Stream;

import org.sonar.check.RuleProperty;

public class SystemClassCoupling extends CustomClassCoupling {

	private static final String DEFAULT_PACKAGES_TO_BE_IGNORED = "java,javax,org.springframework";

	@RuleProperty(key = "packagesOrClassesToBeIgnored", description = "Define packages(can be just the start) or classes that should be ignored during the class coupling analysis. If you have more than on, split them with comma. Ex: java,javax,org.springframework,org.hibernate", defaultValue = DEFAULT_PACKAGES_TO_BE_IGNORED)
	public String packagesOrClassesToBeIgnored = DEFAULT_PACKAGES_TO_BE_IGNORED;

	@Override
	protected boolean isFullyQualifiedNameIdentifierIsValid(String fullyQualifiedNameIdentifier) {
		if (shouldIgnore(fullyQualifiedNameIdentifier)) {
			return false;
		}

		return super.isFullyQualifiedNameIdentifierIsValid(fullyQualifiedNameIdentifier);
	}

	private boolean shouldIgnore(String fullyQualifiedNameIdentifier) {
		String[] toBeIgnored = packagesOrClassesToBeIgnored.split(",");
		return Stream.of(toBeIgnored)
				.anyMatch(fullyQualifiedNameIdentifier::startsWith);
	}

}
