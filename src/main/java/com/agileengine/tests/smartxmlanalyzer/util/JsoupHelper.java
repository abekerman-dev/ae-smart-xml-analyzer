package com.agileengine.tests.smartxmlanalyzer.util;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsoupHelper {

	private static Logger LOGGER = LoggerFactory.getLogger(JsoupHelper.class);
	private static String CHARSET_NAME = "utf8";

	public static Optional<Element> findElementById(final File htmlFile, final String targetElementId) {
		Document doc = parseFile(htmlFile).get();
		return Optional.of(doc.getElementById(targetElementId));
	}

	public static Optional<Elements> findElementsMatchingAttrKeyValue(final Document doc, final String key,
			final String value) {
		LOGGER.info("Trying to find attributes with (" + key + ", " + value + ")");
		return Optional.of(doc.getElementsByAttributeValue(key, value));
	}

	public static Optional<Document> parseFile(final File file) {
		Optional<Document> doc = Optional.empty();
		try {
			return Optional.of(Jsoup.parse(file, CHARSET_NAME, file.getAbsolutePath()));
		} catch (IOException e) {
			LOGGER.error("Error reading [{}] file", file.getAbsolutePath(), e);
		}

		return doc;
	}
}
