package com.kozitski.xml.command;

import com.kozitski.xml.controller.Router;
import com.kozitski.xml.logic.ChooseParserReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static com.kozitski.xml.command.PathConstant.INDEX_JSP;
import static com.kozitski.xml.command.PathConstant.LOAD_FILE_JSP;

public class ChooseParserCommand implements Command{
    private static final Logger LOGGER = LogManager.getLogger(ChooseParserCommand.class);

    private static final String PARAM_PARSER = "parser";
    private ChooseParserReceiver receiver;

    ChooseParserCommand(ChooseParserReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        String page = INDEX_JSP;

        String parserType = request.getParameter(PARAM_PARSER);

        if(parserType != null && !parserType.isEmpty()){
            if(receiver.isParser(parserType)){
                request.getSession().setAttribute(PARAM_PARSER, parserType);

                page = LOAD_FILE_JSP;
            }
        }

        LOGGER.info("Command worked successfully");

        router.setPagePath(page);
        return router;
    }

}
