package main.camel.beans;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by Mnishko Sergei on 20.05.2016.
 */
@Component
public class finalizeOrderBean {

    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(finalizeOrderBean.class);

    @Autowired
    private JavaMailSender mailSender;

    private SimpleMailMessage confirmationMail = new SimpleMailMessage();

    @Handler
    public void finalizeOrder (Exchange exchange)throws Exception {
        //getting information about payment
        Random random = new Random();
        exchange.getIn().setHeader("Paid", random.nextBoolean());
    }
}
