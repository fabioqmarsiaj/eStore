package com.fabioqmarsiaj.estore.domain;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class PaymentSlip extends Payment {
    private static final long serialVersionUID = 1L;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    public PaymentSlip(){}

    public PaymentSlip(Integer id, PaymentState paymentState, Order order, Date endDate, Date paymentDate) {
        super(id, paymentState, order);
        this.endDate = endDate;
        this.paymentDate = paymentDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
