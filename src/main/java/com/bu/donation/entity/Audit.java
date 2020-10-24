package com.bu.donation.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Audit {
    private int idAudit;
    private BigDecimal amount;
    private Date date;
    private Integer iddonor;
    private Integer idpayment;
    private String userDetails;

    @Id
    @Column(name = "idAudit",  nullable = false)
    public int getIdAudit() {
        return idAudit;
    }

    public void setIdAudit(int idAudit) {
        this.idAudit = idAudit;
    }

    @Basic
    @Column(name = "amount",  nullable = true, precision = 2)
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
    @Column(name = "iddonor",  nullable = true)
    public Integer getIddonor() {
        return iddonor;
    }

    public void setIddonor(Integer iddonor) {
        this.iddonor = iddonor;
    }

    @Basic
    @Column(name = "idpayment", nullable = true)
    public Integer getIdpayment() {
        return idpayment;
    }

    public void setIdpayment(Integer idpayment) {
        this.idpayment = idpayment;
    }

    @Basic
    @Column(name = "userDetails", nullable = true, length = 45)
    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Audit audit = (Audit) o;

        if (idAudit != audit.idAudit) return false;
        if (amount != null ? !amount.equals(audit.amount) : audit.amount != null) return false;
        if (date != null ? !date.equals(audit.date) : audit.date != null) return false;
        if (iddonor != null ? !iddonor.equals(audit.iddonor) : audit.iddonor != null) return false;
        if (idpayment != null ? !idpayment.equals(audit.idpayment) : audit.idpayment != null) return false;
        if (userDetails != null ? !userDetails.equals(audit.userDetails) : audit.userDetails != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAudit;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (iddonor != null ? iddonor.hashCode() : 0);
        result = 31 * result + (idpayment != null ? idpayment.hashCode() : 0);
        result = 31 * result + (userDetails != null ? userDetails.hashCode() : 0);
        return result;
    }
}
