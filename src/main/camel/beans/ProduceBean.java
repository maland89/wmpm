package main.camel.beans;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ProduceBean {
    private static final Logger LOGGER = Logger.getLogger(ProduceBean.class);


    @Handler
    public void DefineDeliveryTime(Exchange exchange) {
        //logging at the beginning of a process
        LOGGER.info(this.getClass().getName().substring(17) + "\t\t\t|\t Order Nr.: " + exchange.getIn().getHeader("orderID"));

//        Integer id = Integer.parseInt(exchange.getIn().getHeader("id").toString());
//        Calendar calendar = Calendar.getInstance();
//        if (carOrderDao.getOrder(id).getColor() =="red"){
//            calendar.add(Calendar.DATE, 15);
//         }else {
//            calendar.add(Calendar.DATE, 25);
//        }
//        if (carOrderDao.getOrder(id).getHorsepower() > 150){
//            calendar.add(Calendar.DATE, 40);
//         }else{
//            calendar.add(Calendar.DATE, 20);
//        }
//        if (carOrderDao.getOrder(id).getModel()== COUPE){
//            calendar.add(Calendar.DATE, 60);
//        }else{
//            calendar.add(Calendar.DATE, 40);
//        }
//        carOrderDao.getOrder(id).setDeliveryDate(new Timestamp(calendar.getTimeInMillis()));
//        carOrderDao.getOrder(id).setStatus(OrderStatus.ASSEMBLING);
//        LOGGER.debug("The delivery time is set: " + calendar.getTime());

    }
}
