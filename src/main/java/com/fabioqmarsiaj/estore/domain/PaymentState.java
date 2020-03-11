package com.fabioqmarsiaj.estore.domain;

public enum PaymentState {

    WAITING(1, "Waiting"),
    FINISHED(2, "Finished"),
    CANCELED(3, "Canceled");

    private int cod;
    private String description;

    PaymentState(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescription () {
        return description;
    }

    public static PaymentState toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (PaymentState x : PaymentState.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + cod);
    }
}
