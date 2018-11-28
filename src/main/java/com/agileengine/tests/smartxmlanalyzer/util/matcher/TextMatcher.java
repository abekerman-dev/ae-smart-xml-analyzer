package com.agileengine.tests.smartxmlanalyzer.util.matcher;

import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TextMatcher extends ElementMatcher {

	public TextMatcher(ElementMatcher nextMatcher) {
		super(nextMatcher);
	}

	@Override
	protected Optional<Element> getFirstMatchingAnchor(Element originAnchor, Document otherSampleDocument) {
		Elements elements = otherSampleDocument.getElementsMatchingText(originAnchor.text());
		
		return elements.size() > 0 ? Optional.of(elements.get(0)) : Optional.empty();
	}

}
