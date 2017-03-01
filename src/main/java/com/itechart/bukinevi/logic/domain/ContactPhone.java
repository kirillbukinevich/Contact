package com.itechart.bukinevi.logic.domain;

import java.io.Serializable;

public class ContactPhone implements Serializable{

    private int id;
    private int employeeID;
    private int codeCountry;
    private int codeOperator;
    private int number;
    private String type;
    private String comment;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getCodeCountry() {
        return this.codeCountry;
    }

    public void setCodeCountry(int codeCountry) {
        this.codeCountry = codeCountry;
    }

    public int getCodeOperator() {
        return this.codeOperator;
    }

    public void setCodeOperator(int codeOperator) {
        this.codeOperator = codeOperator;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ContactPhone{" +
                "id=" + id +
                ", employeeID=" + employeeID +
                ", codeCountry=" + codeCountry +
                ", codeOperator=" + codeOperator +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
