/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.student;

/**
 *
 * @author MinhNBHSE61805
 */
public class StudentDTO {
    private String id;
    private String sClass;
    private String lastname;
    private String middlename;
    private String firstname;
    private String sex;
    private String address;
    private String status;

    public StudentDTO() {
    }

    public StudentDTO(String id, String sClass, String lastname, String middlename, String firstname, String sex, String address, String status) {
        this.id = id;
        this.sClass = sClass;
        this.lastname = lastname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.sex = sex;
        this.address = address;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
