package main.camel.routes;

import main.camel.beans.MyAggregationStrategy;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class QueryStockRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:queryStock")
                .routeId("QueryStockRoute")
                .pollEnrich("jpa:Stock" +
                "?consumer.query=select s from Stock s where s.id=1&consumeDelete=false", new MyAggregationStrategy())
                .choice()
                .when(header("enoughElements").isEqualTo(true))
                    .log(LoggingLevel.INFO,"FILE", "${routeId} \t\t\t\t\t|\t Order Nr.: ${header.orderID} \t|\t From QueryStock to PlanProduction \t|\t")
                    .to("jms:queue:planProduction?messageConverter=#myMessageConverter")
                .otherwise()
                    .log(LoggingLevel.INFO,"FILE", "${routeId} \t\t\t\t\t|\t Order Nr.: ${header.orderID} \t|\t From QueryStock to OrderElements \t|\t")
                    .to("direct:orderElements")
                .endChoice();
    }
}