package com.bu.donation.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Donor {
    private int idDonor;
    private String name;
    private String address;
    private String email;
    private String phone;
    private String type;

    @Id
    @Column(name = "idDonor", nullable = false)
    public int getIdDonor() {
        return idDonor;
    }

    public void setIdDonor(int idDonor) {
        this.idDonor = idDonor;
    }

    @Basic
    @Column(name = "name",  nullable = true, length = 64)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address",  nullable = true, length = 128)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 32)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "type",  nullable = true, length = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Donor donor = (Donor) o;

        if (idDonor != donor.idDonor) return false;
        if (name != null ? !name.equals(donor.name) : donor.name != null) return false;
        if (address != null ? !address.equals(donor.address) : donor.address != null) return false;
        if (email != null ? !email.equals(donor.email) : donor.email != null) return false;
        if (phone != null ? !phone.equals(donor.phone) : donor.phone != null) return false;
        if (type != null ? !type.equals(donor.type) : donor.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDonor;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
