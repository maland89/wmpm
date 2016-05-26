package main.camel.routes;

import main.camel.beans.CreateOrderBean;
import main.camel.beans.ProcessOrderBean;
import org.apache.camel.builder.RouteBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

          from("timer:start?period=10s")
            .bean(CreateOrderBean.class)
            //.bean(processOrderBean)
            .to("direct:processOrder");


//        LOGGER.debug("Starting Jetty server...");
//
//        // define and add the jetty component
//        restConfiguration().component("jetty")
//                .host("{{rest.jetty.host}}")
//                .port("{{rest.jetty.port}}")
//                .bindingMode(RestBindingMode.auto);
//
//        LOGGER.debug("Jetty server started succesfully.");
//
//        // DEFINE BEHAVIOR ON JSON SCHEMA PROBLEMS
//        onException(UnrecognizedPropertyException.class).handled(true)
//                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
//                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
//                .setBody().constant("Invalid json data");
//
//        // DEFINE BEHAVIOR ON DATA MODEL PROBLEMS
//        onException(Exception.class).handled(true)
//                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
//                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
//                .setBody().simple("Invalid data values:\n${exception.message}");
//
//        rest("/services/rest").put("/order").consumes("application/json")
//                .type(CarOrder.class).produces("text/html")
//                .to("direct:order_put");
//
//        from("direct:order_put").routeId("REST")
//            .log("Received REST message with a simple order")
//            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
//            .to("direct:order_processing")
//            .end();
    }
}
