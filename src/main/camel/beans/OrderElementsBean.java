package main.camel.beans;

import main.dao.CustomerDao;
import main.dao.OrderDao;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by maland on 24.05.2016.
 */
@Component
public class OrderElementsBean {

    private static final Logger LOGGER = Logger.getLogger(TestCustomerBean.class);

    @Autowired
    CustomerDao customerDao;

/*    @Autowired
    StockDao stockDao;*/

    @Autowired
    OrderDao orderDao;

    @Handler
    public String process(@Body String order, Exchange exchange)
    {
        return "New ELEMENTS WERE ORDERED - OK!";
    }
}