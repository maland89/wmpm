package main.camel.beans;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component // TODO PLS KILL ME
public class CheckFinancialSolvencyBean {

    private static final Logger LOGGER = Logger.getLogger("FILE");

    @Handler
    public void process (@Body String order, Exchange exchange)
    {
        //logging at the beginning of a process
        LOGGER.info(this.getClass().getName().substring(17) + "\t|\t Order Nr.: " + exchange.getIn().getHeader("orderID"));
        exchange.setOut(exchange.getIn());
        Random random = new Random();

        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        exchange.getOut().setHeader("solvencyApproval", random.nextBoolean());

        //logging at the end of a process
        LOGGER.info(this.getClass().getName().substring(17) + "\t|\t Order Nr.: " + exchange.getOut().getHeader("orderID") + "  \t|\t New Header: solvencyApproval = " + exchange.getOut().getHeader("solvencyApproval").toString());

        //return order + "bar";
        //carOrderDao.insertOrder(order);
    }
}
