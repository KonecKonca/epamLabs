package com.kozitski.xml.command;

import com.kozitski.xml.controller.Router;
import com.kozitski.xml.entity.LimitTariff;
import com.kozitski.xml.entity.UnlimitTariff;
import com.kozitski.xml.exception.XMLParseException;
import com.kozitski.xml.logic.ShowXmlReceiver;
import com.kozitski.xml.util.FileNameGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.List;

import static com.kozitski.xml.command.PathConstant.LOAD_FILE_JSP;
import static com.kozitski.xml.command.PathConstant.SHOW_TARIFF_JSP;

public class ShowXmlCommand implements Command{
    private static final Logger LOGGER = LogManager.getLogger(Command.class);
    private static final String PARAM_PARSER = "parser";

    private static final String DIRECTORY_NAME = "data";
    private static final String LIMIT_LIST = "limitList";
    private static final String UNLIMIT_LIST = "unlimitList";

    private ShowXmlReceiver receiver;

    ShowXmlCommand(ShowXmlReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public Router execute(HttpServletRequest request){
        Router router = new Router();
        String page = LOAD_FILE_JSP;

        String parserType = (String) request.getSession().getAttribute(PARAM_PARSER);

        if(parserType != null && !parserType.isEmpty()){

            String loadXmlPath = request.getServletContext().getRealPath("") + DIRECTORY_NAME + File.separator + FileNameGenerator.getLastName();
            try {
                receiver.init(parserType, loadXmlPath);
            }
            catch (XMLParseException e) {
                LOGGER.error("Can not read xml file", e);
                return router;
            }

            List<LimitTariff> limitTariffs = receiver.getLimitTariffs();
            List<UnlimitTariff> unlimitTariffs = receiver.getUnlimitTariffs();

            request.setAttribute(LIMIT_LIST, limitTariffs);
            request.setAttribute(UNLIMIT_LIST, unlimitTariffs);

            page = SHOW_TARIFF_JSP;
        }

        LOGGER.info("Command worked successfully");

        router.setPagePath(page);
        return router;
    }

}


