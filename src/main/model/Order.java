package main.model;

/**
 * Created by Polina on 14/05/2016.
 */

import main.model.enums.CarModel;
import main.model.enums.OrderStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@org.hibernate.annotations.Entity(dynamicUpdate = true)
@Table(name = "Order", uniqueConstraints = {@UniqueConstraint(columnNames = "ID")})
public class Order {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @ManyToOne (fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_FK", columnDefinition = "INT(11)")
    private Customer customerFK;

    @Column(name = "ORDER_DATE", unique = false, nullable = false)
    private Timestamp orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", unique = false, nullable = false, length = 100)
    private OrderStatus status;

    @Column(name = "CREDIT_NEEDED", columnDefinition = "TINYINT(1)")
    private Boolean creditNeeded;

    @Column(name = "COLOR", unique = false, nullable = false, length = 100)
    private String color;

    @Column(name = "HORSEPOWER", unique = false, nullable = false)
    private Integer horsepower;

    @Enumerated(EnumType.STRING)
    @Column(name = "MODEL", unique = false, nullable = false)
    private CarModel model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomerFK() {
        return customerFK;
    }

    public void setCustomerFK(Customer customerFK) {
        this.customerFK = customerFK;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Boolean getCreditNeeded() {
        return creditNeeded;
    }

    public void setCreditNeeded(Boolean creditNeeded) {
        this.creditNeeded = creditNeeded;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerFK=" + customerFK +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", creditNeeded=" + creditNeeded +
                ", color='" + color + '\'' +
                ", horsepower=" + horsepower +
                ", model=" + model +
                '}';
    }
}
