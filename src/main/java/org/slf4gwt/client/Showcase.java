package org.slf4gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Showcase implements EntryPoint {


  private static final Logger ROOT_LOGGER = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
  private static final Logger NORMAL_LOGGER = LoggerFactory.getLogger(Showcase.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final List<Button> logRootMessageButtonList = createButtonGroup(ROOT_LOGGER);
    final List<Button> logParentMessageButtonList = createButtonGroup(NORMAL_LOGGER);

    logRootMessageButtonList.forEach(RootPanel.get("rootLoggerButton")::add);
    logParentMessageButtonList.forEach(RootPanel.get("loggerButton")::add);
  }

  private void modifyButton(Button button, String color) {
    button.removeStyleName("gwt-Button");
    button.addStyleName("btn");
    button.addStyleName(color);
    button.getElement().setAttribute("type","button");
  }

  private List<Button> createButtonGroup(Logger logger) {
    Button traceLogButton = new Button("trace");
    modifyButton(traceLogButton,"btn-primary");
    traceLogButton.addClickHandler(event -> logger.trace("{} trace log", logger.getName()));
    Button debugLogButton = new Button("debug");
    modifyButton(debugLogButton,"btn-secondary");
    debugLogButton.addClickHandler(event -> logger.debug("{} debug log message", logger.getName()));
    Button infoLogButton = new Button("info");
    modifyButton(infoLogButton, "btn-info");
    infoLogButton.addClickHandler(event -> logger.info("{} info log message", logger.getName()));
    Button warnLogButton = new Button("warn");
    modifyButton(warnLogButton,"btn-warning");
    warnLogButton.addClickHandler(event -> logger.warn("{} warn log message", logger.getName(), new Throwable()));
    Button errorLogButton = new Button("error");
    modifyButton(errorLogButton, "btn-danger");
    errorLogButton.addClickHandler(event -> logger.error("{} error log message", logger.getName(), new NullPointerException()));
    return Arrays.asList(traceLogButton, debugLogButton, infoLogButton, warnLogButton, errorLogButton);
  }
}
