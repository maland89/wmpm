package main.camel.beans;

import main.dao.CarOrderDao;
import main.dao.CustomerDao;
import main.model.CarOrder;
import main.model.Customer;
import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * Starting condition: Any component sends a message to the processor
 * consisting of message body and receiver.
 *
 * Result: Consumer will receive an email consisting of the relevant mail.
 * Process: Extract information on message body and recipient from message.
 * Retrieve mail address from user database. Create and dispatch mail using SMTP.
 */
@Component
public class InformCustomerBean {

    private static final Logger LOGGER = Logger.getLogger(InformCustomerBean.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CarOrderDao carOrderDao;

    private SimpleMailMessage confirmationMail = new SimpleMailMessage();

    @Handler
    public void process(Exchange exchange)
    {
        int orderId = (int) exchange.getIn().getHeader("orderID");

        CarOrder order = carOrderDao.getOrder(orderId);
        Customer customer = order.getCustomerFK();

        LOGGER.debug("E-Mail to " + customer.getEmail() + " for OrderID=" + order.getId());

        this.confirmationMail.setTo(customer.getEmail());
        this.confirmationMail.setSubject(order.getId() + " - " + order.getStatus().name());
        this.confirmationMail.setText("We are pleased to inform you about your car\n\n" +
        order.toString() + "\n\n" +
        "Smart Car Company GmbH");

        this.mailSender.send(this.confirmationMail);

        LOGGER.debug("E-Mail sent ...");

    }
}
