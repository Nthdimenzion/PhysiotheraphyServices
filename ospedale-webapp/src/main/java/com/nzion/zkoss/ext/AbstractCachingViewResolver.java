package com.nzion.zkoss.ext;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.sys.ExecutionsCtrl;
import org.zkoss.zul.Window;

import com.nzion.util.UtilValidator;

public abstract class AbstractCachingViewResolver {
	
	public void navigate(String viewName, Map<?, ?> arguments) {
	navigate(viewName, arguments, (String)null);
	}

	public void navigate(String viewName, Map<?, ?> arguments, Component container) {
	if(container != null && UtilValidator.isNotEmpty(container.getChildren()))
		container.getChildren().clear();
	Executions.getCurrent().createComponents(resolveViewName(viewName),container, arguments);
	}

	public void navigate(String viewName, String defaultViewName, Map<?, ?> arguments, Component container) {
	if(container != null && UtilValidator.isNotEmpty(container.getChildren()))
		container.getChildren().clear();
	Executions.getCurrent().createComponents(resolveViewName(viewName, defaultViewName),container, arguments);
	}

	public void navigate(String viewName, Map<?, ?> arguments,String containerName) {
	if(UtilValidator.isEmpty(containerName))
		containerName="center";
	Component component = ExecutionsCtrl.getCurrentCtrl().getCurrentPage().getFellow(containerName, true);
	navigate(viewName, arguments, component);
	}

	public Window getWindowForURI(String uri, Map<?, ?> arguments) {
	return (Window) Executions.getCurrent().createComponents(uri.trim(), null, arguments);
	}

	public Window getWindowForViewName(String viewName, Map<?, ?> arguments) {
	return getWindowForURI(resolveViewName(viewName), arguments);
	}

	abstract public boolean viewExists(String viewName);

	abstract protected String resolveViewName(String viewName);
	
	abstract protected String resolveViewName(String viewName, String defaultViewName);

	public void navigate(String viewName, Map<?, ?> arguments, Component container, boolean append) {
	Executions.getCurrent().createComponents(resolveViewName(viewName),container, arguments);
	}
}