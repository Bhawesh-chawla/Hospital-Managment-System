/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.dao;

import Sanjeevaniapp.dbutil.DBConnection;
import Sanjeevaniapp.pojo.EmpPojo;
import Sanjeevaniapp.pojo.UserDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class ReceptionistDAO {
    public static boolean addReceptionist(UserDetail user) throws SQLException{
       PreparedStatement ps=DBConnection.getConnetion().prepareStatement("insert into users values(?,?,?,?,?)");
       ps.setString(1, user.getUserid());
       ps.setString(2, user.getUserName());
       ps.setString(3, user.getEmpId());
       ps.setString(4, user.getPassword());
       ps.setString(5, user.getUserType());
       int x=ps.executeUpdate();
       return x>0;
    }    
    
        
public static HashMap<String,EmpPojo>getRecpData() throws SQLException{
    Statement s=DBConnection.getConnetion().createStatement();    
    ResultSet rs=s.executeQuery("Select * from employees where ROLE='RECEPTIONIST' ");
    HashMap<String,EmpPojo> empData=new HashMap();
    while(rs.next()){
            EmpPojo e=new EmpPojo();
            e.setEmpid(rs.getString(1));
            e.setEmpname(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
            empData.put(rs.getString(1),e);
}
return empData;
}
public static boolean deleteRecp(EmpPojo e) throws SQLException{
    PreparedStatement ps=DBConnection.getConnetion().prepareStatement("delete from users where EMPID=?");
    ps.setString(1,e.getEmpid());
    int result=ps.executeUpdate();
    PreparedStatement ps1=DBConnection.getConnetion().prepareStatement("delete from employees where EMPID=?");
    ps1.setString(1,e.getEmpid());
    int result1=ps1.executeUpdate();
    return (result==1&&result1==1);
}
    public static ArrayList<EmpPojo> getAllRecp() throws SQLException{
        Statement st=DBConnection.getConnetion().createStatement();
        ResultSet rs=st.executeQuery("select * from employees where ROLE='RECEPTIONIST'");
        ArrayList<EmpPojo> empList=new ArrayList<>();
        while(rs.next()){
            EmpPojo e=new EmpPojo();
            e.setEmpid(rs.getString(1));
            e.setEmpname(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSal(rs.getDouble(4));
            empList.add(e);
        }
        return empList;
    }
}
