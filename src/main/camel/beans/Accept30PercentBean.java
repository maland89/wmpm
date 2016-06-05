package main.camel.beans;

import main.dao.CarOrderDao;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Accept30PercentBean {

    private static final Logger LOGGER = Logger.getLogger("FILE");

    @Autowired
    private CarOrderDao carOrderDao;

    @Handler
    public String process (@Body String order, Exchange exchange)
    {
        //logging at the beginning of a process
        LOGGER.info(this.getClass().getName().substring(17) + "\t\t\t|\t Order Nr.: " + exchange.getIn().getHeader("orderID"));

        return "New ELEMENTS WERE ORDERED - OK!";
    }

}
