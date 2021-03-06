package com.nzion.zkoss.ext;

import java.io.IOException;
import java.util.Properties;

public class ResourceBundleViewResolver extends AbstractCachingViewResolver {

	private Properties properties = new Properties();

	public void setBasename(String basename) {
	ClassLoader bundleClassLoader = Thread.currentThread().getContextClassLoader();
	try {
		properties.load(bundleClassLoader.getResourceAsStream(basename));
	} catch (IOException e) {
		throw new RuntimeException(e);
	}
	}

	public void setBasenames(String[] basenames) {
	for(String basename : basenames)
		setBasename(basename);
	}

	@Override
	protected String resolveViewName(String viewName) {
	return properties.getProperty(viewName, viewName + ".zul");
	}

	@Override
	protected String resolveViewName(String viewName, String defaultViewName) {
	String actualViewName = properties.getProperty(viewName);
	if(actualViewName == null)
		actualViewName = properties.getProperty(defaultViewName);
	return actualViewName;
	}

	@Override
	public boolean viewExists(String viewName) {
	return properties.getProperty(viewName) != null;
	}
}