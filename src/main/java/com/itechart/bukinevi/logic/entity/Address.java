package com.itechart.bukinevi.logic.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class Address implements Serializable{
    private int id;
    private String countryName = "";
    private String cityName = "";
    private String streetName = "";
    private String houseNumber = "";
    private int flatNumber;
    private int index;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return this.flatNumber==0 ? null:flatNumber;
    }

    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getIndex() {
        return this.index==0 ? null:index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if(StringUtils.isNotEmpty(countryName)){
            result.append(countryName);
        }
        if(StringUtils.isNotEmpty(cityName)){
            result.append(" город: ").append(cityName);
        }
        if(StringUtils.isNotEmpty(streetName)){
            result.append(" улица: ").append(streetName);
        }
        if(StringUtils.isNotEmpty(houseNumber)){
            result.append(" дом: ").append(houseNumber);
        }
        if(flatNumber!=0){
            result.append(" квартира: ").append(flatNumber);
        }

        return result.toString();
    }
}
