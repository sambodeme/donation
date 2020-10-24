package com.bu.donation.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Payment {
    private int idPayment;
    private String name;

    @Id
    @Column(name = "idPayment",  nullable = false)
    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    @Basic
    @Column(name = "name",  nullable = true, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (idPayment != payment.idPayment) return false;
        if (name != null ? !name.equals(payment.name) : payment.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPayment;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
