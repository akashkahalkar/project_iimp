package com.liveproject.persi.ycce.iimp;

/**
 * Created by Tiger on 06-10-2016.
 */
public class Member {

    String id, firstname, lastname, dob, gender, doj, mobileno, emailid;
    String designation, division, subdivision, supervisior;
    String addrline, street, locality, city, state, country, pincode;

    public Member() {
    }

    public Member(String id, String firstname, String lastname, String dob, String gender, String doj, String mobileno, String emailid, String designation, String division, String subdivision, String supervisior, String addrline, String street, String locality, String city, String state, String country, String pincode) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.gender = gender;
        this.doj = doj;
        this.mobileno = mobileno;
        this.emailid = emailid;
        this.designation = designation;
        this.division = division;
        this.subdivision = subdivision;
        this.supervisior = supervisior;
        this.addrline = addrline;
        this.street = street;
        this.locality = locality;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }

    public Member(Member m){
        this.id = m.getId();
        this.firstname = m.getFirstname();
        this.lastname = m.getLastname();
        this.dob = m.getDob();
        this.gender = m.getGender();
        this.doj = m.getDoj();
        this.mobileno = m.getMobileno();
        this.emailid = m.getEmailid();
        this.designation = m.getDesignation();
        this.division = m.getDivision();
        this.subdivision = m.getSubdivision();
        this.supervisior = m.getSupervisior();
        this.addrline = m.getAddrline();
        this.street = m.getStreet();
        this.locality = m.getLocality();
        this.city = m.getCity();
        this.state = m.getState();
        this.country = m.getCountry();
        this.pincode = m.getPincode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public String getSupervisior() {
        return supervisior;
    }

    public void setSupervisior(String supervisior) {
        this.supervisior = supervisior;
    }

    public String getAddrline() {
        return addrline;
    }

    public void setAddrline(String addrline) {
        this.addrline = addrline;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
