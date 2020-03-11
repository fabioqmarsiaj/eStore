package com.fabioqmarsiaj.estore.domain;

import javax.persistence.*;

@Entity
public class Address {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street;
    private String number;
    private String complement;
    private String neighboor;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private City city;

    public Address() {}

    public Address(Integer id, String street, String number, String complement, String neighboor, String zipCode,
                    Client client, City city) {
        super();
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighboor = neighboor;
        this.zipCode = zipCode;
        this.client = client;
        this.setCity(city);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }


    public void setComplement(String complement) {
        this.complement = complement;
    }


    public String getNeighboor() {
        return neighboor;
    }

    public void setNeighboor(String neighboor) {
        this.neighboor = neighboor;
    }

    public String getZipCode() {
        return zipCode;
    }


    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
