/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.pojo;

/**
 *
 * @author HP
 */
public class UserDetail {

    @Override
    public String toString() {
        return "UserDetail{" + "userid=" + userid + ", password=" + password + ", userType=" + userType + ", userName=" + userName + ", empId=" + empId + '}';
    }
   private String userid;
   private String password;
   private String userType;
   private String userName;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
   private String empId;
   
    
}
