package com.agileengine.tests.smartxmlanalyzer.util.matcher;

import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public abstract class AttributeMatcher extends ElementMatcher {

	public AttributeMatcher(ElementMatcher nextMatcher) {
		super(nextMatcher);
	}

	@Override
	protected Optional<Element> getFirstMatchingAnchor(Element originAnchor, Document otherSampleDocument) {
		String attrName = getAttributeNameFromMatcherSubclass();
		Elements elements = otherSampleDocument.getElementsByAttributeValue(attrName, originAnchor.attr(attrName));
		
		return elements.size() > 0 ? Optional.of(elements.get(0)) : Optional.empty();
	}

	private String getAttributeNameFromMatcherSubclass() {
		return getClass().getSimpleName().replaceAll("Matcher", "").toLowerCase();
	}

}
