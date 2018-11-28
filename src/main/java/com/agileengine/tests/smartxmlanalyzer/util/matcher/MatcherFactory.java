package com.agileengine.tests.smartxmlanalyzer.util.matcher;

public class MatcherFactory {

	private MatcherFactory() {
	}

	public static ElementMatcher getMatcherChain() {
		final ElementMatcher hrefMatcher = new HrefMatcher(null);
		final ElementMatcher classMatcher = new ClassMatcher(hrefMatcher);
		final ElementMatcher titleMatcher = new TitleMatcher(classMatcher);
		
		return new TextMatcher(titleMatcher);
	}

}
