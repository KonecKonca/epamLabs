package com.kozitski.xml.command;

import com.kozitski.xml.controller.Router;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request);

}
