package com.deveficiente.designprinciples.checks.java.design;

public class SystemClassCoupling extends CustomClassCoupling{
	
	@Override
	protected boolean isFullyQualifiedNameIdentifierIsValid(String fullyQualifiedNameIdentifier) {
		if(isJavaRuntimeClass(fullyQualifiedNameIdentifier) 
				|| isSpringClass(fullyQualifiedNameIdentifier)) {
			return false;
		}
		
		System.out.println(fullyQualifiedNameIdentifier);
		return super.isFullyQualifiedNameIdentifierIsValid(fullyQualifiedNameIdentifier);
	}

	private boolean isSpringClass(String fullyQualifiedNameIdentifier) {
		return fullyQualifiedNameIdentifier.startsWith("org.springframework");
	}

	private boolean isJavaRuntimeClass(String fullyQualifiedNameIdentifier) {
		return fullyQualifiedNameIdentifier.startsWith("java") 
				|| 
			   fullyQualifiedNameIdentifier.startsWith("javax")	;
	}
	

}
