//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.logic.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Employee implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String familyStatus;
    private String webSite;
    private String email;
    private String workPlace;
    private Address address = new Address();
    private List<ContactPhone> phoneList = new ArrayList<>();
    private List<Attachment> attachmentList = new ArrayList<>();
    private String photoName;
    private Photo photo;

    public Employee() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFamilyStatus() {
        return this.familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getWebSite() {
        return this.webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkPlace() {
        return this.workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public List<ContactPhone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<ContactPhone> phoneList) {
        this.phoneList = phoneList;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String toString() {
        return "Employee{id=" + this.id + ", firstName=\'" + this.firstName + '\'' + ", lastName=\'" + this.lastName + '\'' + ", patronymic=\'" + this.patronymic + '\'' + ", dateOfBirth=" + this.dateOfBirth + ", gender=\'" + this.gender + '\'' + ", nationality=\'" + this.nationality + '\'' + ", familyStatus=\'" + this.familyStatus + '\'' + ", webSite=\'" + this.webSite + '\'' + ", email=\'" + this.email + '\'' + ", workPlace=\'" + this.workPlace + '\'' + ", address=" + this.address + '}';
    }


}
