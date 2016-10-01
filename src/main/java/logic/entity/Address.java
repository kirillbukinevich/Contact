package logic.entity;

public class Address {
    private int id;
    private String countryName;
    private String cityName;
    private String streetName;
    private Integer houseNumber;
    private Integer flatNumber;
    private Integer index;

    public Address() {
    }

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

    public Integer getHouseNumber() {
        return this.houseNumber==0 ? null:houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getFlatNumber() {
        return this.flatNumber==0 ? null:flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    public Integer getIndex() {
        return this.index==0 ? null:index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }


    public String toString() {
        StringBuilder result = new StringBuilder();
        if(countryName!=null){
            result.append(countryName);
        }
        if(cityName!=null){
            result.append(" г.").append(cityName);
        }
        if(streetName!=null){
            result.append(" ул.").append(streetName);
        }
        if(houseNumber!=null){
            result.append(" д.").append(houseNumber);
        }
        if(flatNumber!=null){
            result.append(" кв.").append(flatNumber);
        }

        return result.toString();
    }

}
