package com.bu.donation.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Donation {
    private int idDonation;
    private BigDecimal amount;
    private Date date;
    private int iddonor;
    private int idpayment;
    private Donor donorByIddonor;
    private Payment paymentByIdpayment;

    @Id
    @Column(name = "idDonation", nullable = false)
    public int getIdDonation() {
        return idDonation;
    }

    public void setIdDonation(int idDonation) {
        this.idDonation = idDonation;
    }

    @Basic
    @Column(name = "amount",nullable = true, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "date",  nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "iddonor", nullable = false)
    public int getIddonor() {
        return iddonor;
    }

    public void setIddonor(int iddonor) {
        this.iddonor = iddonor;
    }

    @Basic
    @Column(name = "idpayment",  nullable = false)
    public int getIdpayment() {
        return idpayment;
    }

    public void setIdpayment(int idpayment) {
        this.idpayment = idpayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Donation donation = (Donation) o;

        if (idDonation != donation.idDonation) return false;
        if (iddonor != donation.iddonor) return false;
        if (idpayment != donation.idpayment) return false;
        if (amount != null ? !amount.equals(donation.amount) : donation.amount != null) return false;
        if (date != null ? !date.equals(donation.date) : donation.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDonation;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + iddonor;
        result = 31 * result + idpayment;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "iddonor", referencedColumnName = "idDonor", nullable = false, insertable =  false, updatable = false)
    public Donor getDonorByIddonor() {
        return donorByIddonor;
    }

    public void setDonorByIddonor(Donor donorByIddonor) {
        this.donorByIddonor = donorByIddonor;
    }

    @ManyToOne
    @JoinColumn(name = "idpayment", referencedColumnName = "idPayment", nullable = false, insertable =  false, updatable = false)
    public Payment getPaymentByIdpayment() {
        return paymentByIdpayment;
    }

    public void setPaymentByIdpayment(Payment paymentByIdpayment) {
        this.paymentByIdpayment = paymentByIdpayment;
    }
}
