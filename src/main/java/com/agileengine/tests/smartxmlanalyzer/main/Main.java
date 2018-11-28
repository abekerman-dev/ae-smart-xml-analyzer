package com.agileengine.tests.smartxmlanalyzer.main;

import static com.agileengine.tests.smartxmlanalyzer.util.JsoupHelper.findElementById;
import static com.agileengine.tests.smartxmlanalyzer.util.JsoupHelper.parseFile;
import static com.agileengine.tests.smartxmlanalyzer.util.matcher.MatcherFactory.getMatcherChain;

import java.io.File;
import java.util.Optional;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.agileengine.tests.smartxmlanalyzer.util.PropertiesReader;
import com.agileengine.tests.smartxmlanalyzer.util.matcher.ElementMatcher;

public class Main {

	private static final String OK_BUTTON_ID = "make-everything-ok-button.id";

	private static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			throw new IllegalArgumentException("Usage: Main <input_origin_file_path> <input_other_sample_file_path>");
		}

		String targetElementId = PropertiesReader.getApplicationProperties().getProperty(OK_BUTTON_ID);

		final String originFilePath = args[0];
		final String otherSampleFilePath = args[1];

		final Element originAnchor = findElementById(new File(originFilePath), targetElementId).get();
		final Document otherSampleDocument = parseFile(new File(otherSampleFilePath)).get();
		
		final ElementMatcher chainOfMatchers = getMatcherChain();
		Optional<Element> matchingElementInSample = chainOfMatchers.runMatchChain(originAnchor, otherSampleDocument);
		log.info(matchingElementInSample.toString());
	}

}
