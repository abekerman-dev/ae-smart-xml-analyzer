package com.agileengine.tests.smartxmlanalyzer.util.matcher;

import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ElementMatcher {

	private static Logger log = LoggerFactory.getLogger(ElementMatcher.class);
	
	protected Optional<ElementMatcher> nextMatcher;

	public ElementMatcher(ElementMatcher nextMatcher) {
		this.nextMatcher = Optional.ofNullable(nextMatcher);
	}

	protected abstract Optional<Element> getFirstMatchingAnchor(Element originAnchor, Document otherSampleDocument);

	public Optional<Element> runMatchChain(Element originAnchor, Document otherSampleDocument) {
		log.info("Trying to match sample against origin with " + getClass().getSimpleName());
		Optional<Element> matchingElement = getFirstMatchingAnchor(originAnchor, otherSampleDocument);
		if (matchingElement.isPresent()) {
			log.info("Origin matches with sample! | matching element: " + matchingElement.toString());
			return matchingElement;
		} else if (nextMatcher.isPresent()) {
			log.info("Match not found, falling back to next matcher in chain");
			return nextMatcher.get().runMatchChain(originAnchor, otherSampleDocument);
		} else {
			log.info("No match found!");
			return Optional.empty();
		}
	}

}
