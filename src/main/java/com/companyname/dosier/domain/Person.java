package com.companyname.dosier.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Comparable<Person>{

    private String id;
    private String fullName;
    private String nationalID;
    private Date birthDate;
    private Integer phone;
    private String address;
    private String position;
    private Date registrationDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        String birthDatePattern = "MMM-dd-yyyy";
        String registrationDatePattern = "MMM-dd-yyyy HH:mm:ss";
        SimpleDateFormat birthDateSDF = new SimpleDateFormat(birthDatePattern);
        SimpleDateFormat registrationDateSDF = new SimpleDateFormat(registrationDatePattern);
        if(id!=null)    {
            return id + '\t' +
                    fullName + '\t' +
                    nationalID + '\t' +
                    birthDateSDF.format(birthDate) + '\t' +
                    phone + '\t' +
                    address + '\t' +
                    position + '\t' +
                    registrationDateSDF.format(registrationDate);
        }
        return fullName + '\t' +
                nationalID + '\t' +
                birthDateSDF.format(birthDate) + '\t' +
                phone + '\t' +
                address + '\t' +
                position;
    }

    @Override
    public int compareTo(Person o) {
        return getFullName().compareTo(o.getFullName());
    }
}
