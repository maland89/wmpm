package main.camel.routes;


import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 1.	Starting condition: Order is received
 2.	Process:
 3.	Result: Backup for order is created in /orders

 */
@Component
public class backupOrderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("seda:backupOrder")
                .routeId("BackupOrder")
                .convertBodyTo(String.class)
                //.convertBodyTo(CarOrder.class).marshal().json()
                .log(LoggingLevel.INFO,"FILE", "${routeId} \t\t\t\t\t\t|\t OrderID.: ${header.orderID} \t|\t Saving Order as ${date:now:yyyyMMdd}-${header.orderID}.txt \t|\t")
                .to("file:backup/orders/?fileName=${date:now:yyyyMMdd}-${header.orderID}.txt&autoCreate=true");
    }
}
