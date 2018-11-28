package com.agileengine.tests.smartxmlanalyzer.util;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesReader {

	private static Logger LOGGER = LoggerFactory.getLogger(PropertiesReader.class);

	private static final Properties properties = new Properties();

	public static Properties getApplicationProperties() throws Exception {
		try (final InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("application.properties")) {
			properties.load(in);
			in.close();
		} catch (Exception e) {
			LOGGER.error("Failed to read properties file", e);
			throw e;
		}

		return properties;
	}
}
