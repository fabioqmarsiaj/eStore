package com.fabioqmarsiaj.estore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer paymentState;

    @JsonIgnore
    @JoinColumn(name = "order_id")
    @OneToOne
    @MapsId
    private Order order;

    public Payment(){}

    public Payment(Integer id, PaymentState paymentState, Order order) {
        super();
        this.id = id;
        this.paymentState = paymentState.getCod();
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getPaymentState() {
        return PaymentState.toEnum(paymentState);
    }

    public void setPaymentState(PaymentState paymentState) {
        this.paymentState = paymentState.getCod();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Payment other = (Payment) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
