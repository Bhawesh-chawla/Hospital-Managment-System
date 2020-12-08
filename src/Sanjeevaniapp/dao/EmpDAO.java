/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.dao;

import Sanjeevaniapp.dbutil.DBConnection;
import Sanjeevaniapp.pojo.EmpPojo;
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
public class EmpDAO {
    public static String getNewID() throws SQLException{
        Statement s=DBConnection.getConnetion().createStatement();
        ResultSet rs=s.executeQuery("Select max(EmpID) from employees");
        int id=1;
        rs.next();
        String empid=rs.getString(1);
        int eno=Integer.parseInt(empid.substring(1));
        id =id+eno;
        String sr="E"+id;
        System.out.println(sr);
        return sr;
        }
    
    public static boolean addEmp(EmpPojo e) throws SQLException{
        PreparedStatement ps=DBConnection.getConnetion().prepareStatement("insert into employees values (?,?,?,?)");
        ps.setString(1,e.getEmpid());
        ps.setString(2,e.getEmpname());
        ps.setString(3,e.getJob());
        ps.setDouble(4,e.getSal());
        int x =ps.executeUpdate();
        return x==1;
        }
    public static ArrayList<EmpPojo> getAllEmp() throws SQLException{
        Statement st=DBConnection.getConnetion().createStatement();
        ResultSet rs=st.executeQuery("select * from employees");
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
    
public static HashMap<String,EmpPojo>getAllEmpData() throws SQLException{
    Statement s=DBConnection.getConnetion().createStatement();    
    ResultSet rs=s.executeQuery("Select * from employees");
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

public static HashMap<String,String> getnNonRegisteredReceptionist() throws SQLException{
    String qry="Select empid,empname from employees where role='RECEPTIONIST' and empid not in (select empid from users where usertype='RECEPTIONIST')";
    Statement st=DBConnection.getConnetion().createStatement();
    ResultSet rs=st.executeQuery(qry);
    HashMap<String,String> receptionist=new HashMap();
    while(rs.next()){
        String id=rs.getString(1);
        String name=rs.getString(2);
        receptionist.put(id, name);
    }
    return receptionist;
}
public static boolean updateEmp(EmpPojo ex) throws SQLException{
    PreparedStatement ps=DBConnection.getConnetion().prepareStatement("update employees set EMPNAME=?,ROLE=?,SAL=? where EMPID=? ");
    ps.setString(1, ex.getEmpname());
    ps.setString(2, ex.getJob());
    ps.setDouble(3, ex.getSal());
    ps.setString(4, ex.getEmpid());
    int result=ps.executeUpdate();
    PreparedStatement ps1=DBConnection.getConnetion().prepareStatement("update users set USERNAME=? where EMPID=? ");
    ps1.setString(1, ex.getEmpname());
    ps1.setString(2, ex.getEmpid());
    int result1=ps1.executeUpdate();
    return result==1&&result1==1;
}
public static boolean deleteEmp(EmpPojo e) throws SQLException{
    PreparedStatement ps=DBConnection.getConnetion().prepareStatement("delete from employees where EMPID=?");
    ps.setString(1,e.getEmpid());
    int result=ps.executeUpdate();
    return result==1;
}
}
