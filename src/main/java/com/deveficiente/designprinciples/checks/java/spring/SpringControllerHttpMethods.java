package com.deveficiente.designprinciples.checks.java.spring;

import org.sonar.plugins.java.api.semantic.SymbolMetadata;

public class SpringControllerHttpMethods {

	/**
	 * Verify if a metadata is annotated with some http verb.
	 * 
	 * @param metadata
	 * @return
	 */
	public static boolean exists(SymbolMetadata metadata) {
		return metadata.isAnnotatedWith("org.springframework.web.bind.annotation.RequestMapping")
				|| metadata.isAnnotatedWith("org.springframework.web.bind.annotation.PostMapping")
				|| metadata.isAnnotatedWith("org.springframework.web.bind.annotation.GetMapping")
				|| metadata.isAnnotatedWith("org.springframework.web.bind.annotation.PutMapping")
				|| metadata.isAnnotatedWith("org.springframework.web.bind.annotation.DeleteMapping");

	}

}
