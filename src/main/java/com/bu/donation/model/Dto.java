package com.bu.donation.model;



import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

public class Dto {
    private int firstIntField;
    private int secondIntField;
    private int thirdIntField;
    private int fourthIntField;
    private int fifthIntField;
    private int sixthIntField;

    private String firstStringField;
    private String secondStringField;
    private String thirdStringField;
    private String fourthStringField;
    private String fifthStringField;
    private String sixthStringField;
    private String seventhStringField;

    private String eighthStringField;
    private String ninethStringField;

    private Time   firstTimeField;
    private Time   secondTimeField;

    private BigDecimal firstBigDecimalField;
    private BigDecimal secondBigDecimalField;
    private BigDecimal thirdBigDecimalField;

    private Date firstDateField;
    private Date secondDateField;
    private Date thirdDateField;
    private Date fourthDateField;

    public Dto(){

    }
    public Dto(int firstIntField, int secondIntField, int thirdIntField, int fourthIntField, int fifthIntField, int sixthIntField,
               String firstStringField, String secondStringField,
               Time firstTimeField, Time secondTimeField,
               BigDecimal firstBigDecimalField, BigDecimal secondBigDecimalField, BigDecimal thirdBigDecimalField) {
        this.firstIntField = firstIntField;
        this.secondIntField = secondIntField;
        this.thirdIntField = thirdIntField;
        this.fourthIntField = fourthIntField;
        this.fifthIntField = fifthIntField;
        this.sixthIntField = sixthIntField;
        this.firstStringField = firstStringField;
        this.secondStringField = secondStringField;
        this.firstTimeField = firstTimeField;
        this.secondTimeField = secondTimeField;
        this.firstBigDecimalField = firstBigDecimalField;
        this.secondBigDecimalField = secondBigDecimalField;
        this.thirdBigDecimalField = thirdBigDecimalField;
    }
    public Dto(int firstIntField, String firstStringField) {
        this.firstIntField = firstIntField;
        this.firstStringField = firstStringField;
    }

    public Dto(int firstIntField, int secondIntField, int thirdIntField) {
        this.firstIntField = firstIntField;
        this.secondIntField = secondIntField;
        this.thirdIntField = thirdIntField;
    }
    public Dto(int firstIntField, int secondIntField, String firstStringField) {
        this.firstIntField = firstIntField;
        this.secondIntField = secondIntField;
        this.firstStringField = firstStringField;
    }
    public Dto(int firstIntField, String firstStringField, String secondStringField) {
        this.firstIntField = firstIntField;
        this.firstStringField = firstStringField;
        this.secondStringField = secondStringField;
    }
    public Dto(int firstIntField, int secondIntField, int thirdIntField, Time firstTimeField, Time secondTimeField) {
        this.firstIntField = firstIntField;
        this.secondIntField = secondIntField;
        this.thirdIntField = thirdIntField;
        this.firstTimeField = firstTimeField;
        this.secondTimeField = secondTimeField;
    }
    public Dto(int firstIntField, int secondIntField, int thirdIntField, String firstStringField) {
        this.firstIntField = firstIntField;
        this.secondIntField = secondIntField;
        this.thirdIntField = thirdIntField;
        this.firstStringField = firstStringField;
    }
    public Dto(int firstIntField, int secondIntField, int thirdIntField, String firstStringField, BigDecimal firstBigDecimalField) {
        this.firstIntField = firstIntField;
        this.secondIntField = secondIntField;
        this.thirdIntField = thirdIntField;
        this.firstStringField = firstStringField;
        this.firstBigDecimalField = firstBigDecimalField;
    }
    public Dto(int firstIntField, int secondIntField, int thirdIntField, int fourthIntField, String firstStringField, String secondStringField, BigDecimal firstBigDecimalField) {
        this.firstIntField = firstIntField;
        this.secondIntField = secondIntField;
        this.thirdIntField = thirdIntField;
        this.fourthIntField = fourthIntField;
        this.firstStringField = firstStringField;
        this.secondStringField = secondStringField;
        this.firstBigDecimalField = firstBigDecimalField;
    }

    public Dto(int firstIntField, int secondIntField, int thirdIntField, int fourthIntField, int fifthIntField, int sixthIntField,
               BigDecimal firstBigDecimalField, BigDecimal secondBigDecimalField, BigDecimal thirdBigDecimalField) {
        this.firstIntField = firstIntField;
        this.secondIntField = secondIntField;
        this.thirdIntField = thirdIntField;
        this.fourthIntField = fourthIntField;
        this.fifthIntField = fifthIntField;
        this.sixthIntField = sixthIntField;
        this.firstBigDecimalField = firstBigDecimalField;
        this.secondBigDecimalField = secondBigDecimalField;
        this.thirdBigDecimalField = thirdBigDecimalField;
    }

    public int getFirstIntField() {
        return firstIntField;
    }

    public void setFirstIntField(int firstIntField) {
        this.firstIntField = firstIntField;
    }

    public int getSecondIntField() {
        return secondIntField;
    }

    public void setSecondIntField(int secondIntField) {
        this.secondIntField = secondIntField;
    }

    public int getThirdIntField() {
        return thirdIntField;
    }

    public void setThirdIntField(int thirdIntField) {
        this.thirdIntField = thirdIntField;
    }

    public int getFourthIntField() {
        return fourthIntField;
    }

    public void setFourthIntField(int fourthIntField) {
        this.fourthIntField = fourthIntField;
    }
    public int getFifthIntField() {
        return fifthIntField;
    }

    public void setFifthIntField(int fifthIntField) {
        this.fifthIntField = fifthIntField;
    }

    public int getSixthIntField() {
        return sixthIntField;
    }

    public void setSixthIntField(int sixthIntField) {
        this.sixthIntField = sixthIntField;
    }

    public String getFirstStringField() {
        return firstStringField;
    }

    public void setFirstStringField(String firstStringField) {
        this.firstStringField = firstStringField;
    }

    public String getSecondStringField() {
        return secondStringField;
    }

    public void setSecondStringField(String secondStringField) {
        this.secondStringField = secondStringField;
    }

    public Time getFirstTimeField() {
        return firstTimeField;
    }

    public void setFirstTimeField(Time firstTimeField) {
        this.firstTimeField = firstTimeField;
    }

    public Time getSecondTimeField() {
        return secondTimeField;
    }

    public void setSecondTimeField(Time secondTimeField) {
        this.secondTimeField = secondTimeField;
    }

    public BigDecimal getFirstBigDecimalField() {
        return firstBigDecimalField;
    }

    public void setFirstBigDecimalField(BigDecimal firstBigDecimalField) {
        this.firstBigDecimalField = firstBigDecimalField;
    }

    public BigDecimal getSecondBigDecimalField() {
        return secondBigDecimalField;
    }

    public void setSecondBigDecimalField(BigDecimal secondBigDecimalField) {
        this.secondBigDecimalField = secondBigDecimalField;
    }

    public BigDecimal getThirdBigDecimalField() {
        return thirdBigDecimalField;
    }

    public void setThirdBigDecimalField(BigDecimal thirdBigDecimalField) {
        this.thirdBigDecimalField = thirdBigDecimalField;
    }
    public Date getFirstDateField() {
        return firstDateField;
    }

    public void setFirstDateField(Date firstDateField) {
        this.firstDateField = firstDateField;
    }

    public Date getSecondDateField() {
        return secondDateField;
    }

    public void setSecondDateField(Date secondDateField) {
        this.secondDateField = secondDateField;
    }
    public String getThirdStringField() {
        return thirdStringField;
    }

    public void setThirdStringField(String thirdStringField) {
        this.thirdStringField = thirdStringField;
    }

    public String getFourthStringField() {
        return fourthStringField;
    }

    public void setFourthStringField(String fourthStringField) {
        this.fourthStringField = fourthStringField;
    }
    public String getFifthStringField() {
        return fifthStringField;
    }

    public void setFifthStringField(String fifthStringField) {
        this.fifthStringField = fifthStringField;
    }

    public String getSixthStringField() {
        return sixthStringField;
    }

    public void setSixthStringField(String sixthStringField) {
        this.sixthStringField = sixthStringField;
    }

    public String getSeventhStringField() {
        return seventhStringField;
    }

    public void setSeventhStringField(String seventhStringField) {
        this.seventhStringField = seventhStringField;
    }

    public String getEighthStringField() {
        return eighthStringField;
    }

    public void setEighthStringField(String eighthStringField) {
        this.eighthStringField = eighthStringField;
    }

    public String getNinethStringField() {
        return ninethStringField;
    }

    public void setNinethStringField(String ninethStringField) {
        this.ninethStringField = ninethStringField;
    }

    public Date getThirdDateField() {
        return thirdDateField;
    }

    public void setThirdDateField(Date thirdDateField) {
        this.thirdDateField = thirdDateField;
    }

    public Date getFourthDateField() {
        return fourthDateField;
    }

    public void setFourthDateField(Date fourthDateField) {
        this.fourthDateField = fourthDateField;
    }

    @Override
    public String toString() {
        return "Dto{" +
                "firstIntField=" + firstIntField +
                ", secondIntField=" + secondIntField +
                ", thirdIntField=" + thirdIntField +
                ", fourthIntField=" + fourthIntField +
                ", fifthIntField=" + fifthIntField +
                ", sixthIntField=" + sixthIntField +
                ", firstStringField='" + firstStringField + '\'' +
                ", secondStringField='" + secondStringField + '\'' +
                ", thirdStringField='" + thirdStringField + '\'' +
                ", fourthStringField='" + fourthStringField + '\'' +
                ", fifthStringField='" + fifthStringField + '\'' +
                ", sixthStringField='" + sixthStringField + '\'' +
                ", seventhStringField='" + seventhStringField + '\'' +
                ", eighthStringField='" + eighthStringField + '\'' +
                ", ninethStringField='" + ninethStringField + '\'' +
                ", firstTimeField=" + firstTimeField +
                ", secondTimeField=" + secondTimeField +
                ", firstBigDecimalField=" + firstBigDecimalField +
                ", secondBigDecimalField=" + secondBigDecimalField +
                ", thirdBigDecimalField=" + thirdBigDecimalField +
                ", firstDateField=" + firstDateField +
                ", secondDateField=" + secondDateField +
                ", thirdDateField=" + thirdDateField +
                ", fourthDateField=" + fourthDateField +
                '}';
    }
}
