package com.fabioqmarsiaj.estore.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name="TB_ORDER")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address addressToDeliver;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> itens = new HashSet<>();

    public Order(){}

    public Order(Integer id, Date date, Client client, Address addressToDeliver) {
        super();
        this.id = id;
        this.date = date;
        this.client = client;
        this.addressToDeliver = addressToDeliver;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddressToDeliver() {
        return addressToDeliver;
    }

    public void setAddressToDeliver(Address addressToDeliver) {
        this.addressToDeliver = addressToDeliver;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<OrderItem> getItens() {
        return itens;
    }

    public void setItens(Set<OrderItem> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
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
