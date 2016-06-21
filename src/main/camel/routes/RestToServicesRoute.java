package main.camel.routes;


import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


@Component
public class RestToServicesRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //errorHandler(deadLetterChannel("seda:errors"));


        // define and add the jetty component
        restConfiguration().component("jetty")
                .host("localhost")
                .port("8181")
                .bindingMode(RestBindingMode.auto);

        // DEFINE BEHAVIOR ON DATA MODEL PROBLEMS
        onException(Exception.class).handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody().simple("Invalid data values:\n${exception.message}");

        // DEFINE BEHAVIOR ON JSON SCHEMA PROBLEMS
        onException(UnrecognizedPropertyException.class).handled(true)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setHeader(Exchange.CONTENT_TYPE, constant("text/plain"))
                .setBody().constant("Invalid json data");

        //JacksonDataFormat jacksondf = new JacksonDataFormat(Blog.class);

        /*rest("/order/")
                .put("/new/")
                .consumes("application/json")
                .type(CarOrder.class).produces("application/json")
                .to("direct:order_put");

        from("direct:order_put").routeId("REST")
                .log("REST message with a car order received")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
                .to("direct:createOrder")
                .log("Response received : ${body}")
                .end();

        /*rest("/user").put("/carOrder").consumes("application/json")
                .type(CarOrder.class).produces("text/html")
                .to("direct:handOverOrder");

        /*from("direct:order_put").routeId("REST")
                .log("Received REST message with a simple order")
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
                .bean(idGeneratorBean)
                .inOnly("seda:confirmation-email.queue")
                .to("direct:order_processing")
                .end();*/

      /*  rest("/rest")
                .post("/customer").consumes("application/json")
                .type(Customer.class).produces("text/html")
                .to("direct:handOverOrder");*/
/*
        from("direct:order_put").routeId("REST")
            .log("Received REST message with a simple order")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
            .to("direct:processOrder");*/


//        from("jetty:http://localhost:8181/mytestservice").process(
//                new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        String message = exchange.getIn().getBody(String.class);
//                        System.out.println("Hello Mr :" + message);
//                        exchange.getOut().setBody("Hello world Mr " + message);
//                    }
//                });

//        restConfiguration().component("jetty").host("0.0.0.0").port("9191").bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true");




//        restConfiguration().component("jetty").host("0.0.0.0").port("9191").bindingMode(RestBindingMode.json).dataFormatProperty("prettyPrint", "true");
//        rest("/say")
//                .get("/hello").to("direct:hello")
//                .get("/bye").consumes("application/json").to("direct:bye")
//                .post("/bye").to("mock:update");
//
//        from("direct:hello")
//                .transform().constant("Hello World");
//        from("direct:bye")
//                .transform().constant("Bye World");



        /*rest("/services/rest").put("/order").consumes("application/json")
                .type(CarOrder.class).produces("text/html")
                .to("direct:order_put");

        from("direct:order_put").routeId("REST")
            .log("Received REST message with a simple order")
            .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201))
            .to("direct:order_processing")
            .end();*/


//        rest("/user").description("User rest service")
//                .consumes("application/json").produces("application/json")
//                .get("/{id}").description("Find user by id").outType()
//                .to("bean:userService?method=getUser(${header.id})");

//                .put().description("Updates or create a user").type(Stock.class)
//                .to("bean:userService?method=updateUser")
//
//                .get("/findAll").description("Find all users").outTypeList(Stock.class)
//                .to("bean:userService?method=listUsers");

    }

}
