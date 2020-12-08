/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.pojo;

import java.sql.Date;

/**
 *
 * @author HP
 */
public class PatientPojo {

    public PatientPojo() {
    }
   public PatientPojo(String pid,  String fname, String sname, int age,String opd,  String gender, String mstatus,Date pdate, String address, String city, String phoneno,String doctorid ) {
        this.pid = pid;
        this.fname = fname;
        this.sname = sname;
        this.opd = opd;
        this.gender = gender;
        this.mstatus = mstatus;
        this.address = address;
        this.city = city;
        this.phoneno = phoneno;
        this.doctorid = doctorid;
        this.pdate = pdate;
        this.age = age;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getOpd() {
        return opd;
    }

    public void setOpd(String opd) {
        this.opd = opd;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }
    private String pid,fname,sname,opd,gender,mstatus,address,city,phoneno,doctorid;
    private Date pdate;
    private int age;
}
