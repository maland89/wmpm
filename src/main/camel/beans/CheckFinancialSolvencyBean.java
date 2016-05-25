package main.camel.beans;

import main.dao.CarOrderDao;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CheckFinancialSolvencyBean {

    private static final Logger LOGGER = Logger.getLogger(ProcessOrderBean.class);

    @Autowired
    private CarOrderDao carOrderDao;

    @Handler
    public void process (@Body String order, Exchange exchange)
    {
        Random random = new Random();

        exchange.getOut().setHeaders(exchange.getIn().getHeaders());
        exchange.getOut().setHeader("solvencyApproval",random.nextBoolean());

        LOGGER.info(this.getClass().getName());
        LOGGER.info("New Header:" + exchange.getOut().getHeaders().toString());

        //return order + "bar";
        //carOrderDao.insertOrder(order);
    }
}
