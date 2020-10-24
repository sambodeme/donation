package com.bu.donation.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
public class Expense {
    private int idExpense;
    private BigDecimal amount;
    private Date date;
    private int idcategory;
    private String comment;
    private Category categoryByIdcategory;

    @Id
    @Column(name = "idExpense", nullable = false)
    public int getIdExpense() {
        return idExpense;
    }

    public void setIdExpense(int idExpense) {
        this.idExpense = idExpense;
    }

    @Basic
    @Column(name = "amount", nullable = false, precision = 2)
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "date",  nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "idcategory", nullable = false)
    public int getIdcategory() {
        return idcategory;
    }

    public void setIdcategory(int idcategory) {
        this.idcategory = idcategory;
    }

    @Basic
    @Column(name = "comment",  nullable = true, length = 128)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (idExpense != expense.idExpense) return false;
        if (idcategory != expense.idcategory) return false;
        if (amount != null ? !amount.equals(expense.amount) : expense.amount != null) return false;
        if (date != null ? !date.equals(expense.date) : expense.date != null) return false;
        if (comment != null ? !comment.equals(expense.comment) : expense.comment != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idExpense;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + idcategory;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idcategory", referencedColumnName = "idCategory", nullable = false, insertable =  false, updatable = false)
    public Category getCategoryByIdcategory() {
        return categoryByIdcategory;
    }

    public void setCategoryByIdcategory(Category categoryByIdcategory) {
        this.categoryByIdcategory = categoryByIdcategory;
    }
}
