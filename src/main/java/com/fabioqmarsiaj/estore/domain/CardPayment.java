package com.fabioqmarsiaj.estore.domain;

import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment{
    private static final long serialVersionUID = 1L;

    private Integer installmentsNumber;

    public CardPayment(){}

    public CardPayment(Integer id, PaymentState paymentState, Order order, Integer installmentsNumber) {
        super(id, paymentState, order);
        this.installmentsNumber = installmentsNumber;
    }

    public Integer getInstallmentsNumber() {
        return installmentsNumber;
    }

    public void setInstallmentsNumber(Integer installmentsNumber) {
        this.installmentsNumber = installmentsNumber;
    }
}
