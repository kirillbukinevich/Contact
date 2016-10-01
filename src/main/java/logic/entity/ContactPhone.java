package logic.entity;

public class ContactPhone {
    private Integer id;
    private Integer employeeID;
    private Integer codeCountry;
    private Integer codeOperator;
    private Integer number;
    private String type;
    private String comment;

    public ContactPhone() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getCodeCountry() {
        return this.codeCountry;
    }

    public void setCodeCountry(Integer codeCountry) {
        this.codeCountry = codeCountry;
    }

    public Integer getCodeOperator() {
        return this.codeOperator;
    }

    public void setCodeOperator(Integer codeOperator) {
        this.codeOperator = codeOperator;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
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
}
