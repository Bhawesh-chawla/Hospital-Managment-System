/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.dao;

import Sanjeevaniapp.dbutil.DBConnection;
import Sanjeevaniapp.pojo.PatientPojo;
import Sanjeevaniapp.pojo.UserProfile;
import Sanjeevaniapp.pojo.userPojo;
import java.sql.Connection;
import java.sql.Date;
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
public class PatientDao {
      
public static boolean addPatient(PatientPojo p) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnetion().prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1,p.getPid());
        ps.setString(2,p.getFname());
        ps.setString(3,p.getSname());
        ps.setInt(4,p.getAge());
        ps.setString(5,p.getOpd());
        ps.setString(6,p.getGender());
        ps.setString(7,p.getMstatus());
        ps.setDate(8,p.getPdate());
        ps.setString(9,p.getAddress());
        ps.setString(10,p.getCity());
        ps.setString(11,p.getPhoneno());
        ps.setString(12,p.getDoctorid());
        /*int result=ps.executeUpdate();
        return result==1;*/
        return (ps.executeUpdate()!=0);
    }
public static boolean updatePatient(PatientPojo p) throws SQLException
{
    PreparedStatement ps=DBConnection.getConnetion().prepareStatement("update patient set F_NAME=?, S_NAME=?,AGE=?,OPD=?,GENDER=?,M_STATUS=?,P_DATE=?,ADDRESS=?,CITY=?,PHONE_NO=?,DOCTOR_ID=? WHERE P_ID=?");
     ps.setString(1,p.getFname());
        ps.setString(2,p.getSname());
        ps.setInt(3,p.getAge());
        ps.setString(4,p.getOpd());
        ps.setString(5,p.getGender());
        ps.setString(6,p.getMstatus());
        ps.setDate(7,p.getPdate());
        ps.setString(8,p.getAddress());
        ps.setString(9,p.getCity());
        ps.setString(10,p.getPhoneno());
        ps.setString(11,p.getDoctorid());
        ps.setString(12,p.getPid());
        return(ps.executeUpdate()!=0);
}
    public static String getNewId() throws SQLException{
        Statement cs=DBConnection.getConnetion().createStatement();
        ResultSet rs=cs.executeQuery("Select max(P_ID) from patient");
        int id=1;
        if(rs.next()){
                if(rs.getString(1)!=null)
                {
                    String empid=rs.getString(1);
                    int eno=Integer.parseInt(empid.substring(1));
                    id=id+eno;
                    String sr="P"+id;
                    return sr;
                }
                else
                   return "P101";
        }
        else
            return "P101";
        
    }
    public static HashMap<String, PatientPojo> getAllPatients() throws SQLException
{
   Connection conn=DBConnection.getConnetion();
    String qry= "select * from patient";
    Statement st=conn.createStatement();
    ResultSet rs= st.executeQuery(qry);
    HashMap<String, PatientPojo> employees= new HashMap<>();
    
    while(rs.next())
    {
      PatientPojo e= new PatientPojo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6), rs.getString(7),rs.getDate(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
        
        employees.put(rs.getString(1),e);
    }
    return employees; 
}
    public static ArrayList<PatientPojo> getAllPatient() throws SQLException{
      Statement st=DBConnection.getConnetion().createStatement();
      ResultSet rs=st.executeQuery("select * from patient"); 
      ArrayList<PatientPojo> patientlist=new ArrayList<>();
      while(rs.next()){
       PatientPojo e= new PatientPojo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6), rs.getString(7),rs.getDate(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
       patientlist.add(e);
      }
      return patientlist;
    }
    public static ArrayList<PatientPojo> AppointmentList() throws SQLException{
        
        PreparedStatement ps=DBConnection.getConnetion().prepareStatement("select DOCTORID from doctors where USERID=?");
        ps.setString(1,UserProfile.getUserid());
        ResultSet rs=ps.executeQuery();
        rs.next();
        String s=rs.getString(1);
        PreparedStatement ps1=DBConnection.getConnetion().prepareStatement("select P_ID,F_NAME,S_NAME,OPD from patient where DOCTOR_ID=?");
        ps1.setString(1,s);
        ResultSet rs1=ps1.executeQuery();
        ArrayList<PatientPojo> pList=new ArrayList<>();
        while(rs1.next())
        {
           PatientPojo p=new PatientPojo();
           p.setPid(rs1.getString(1));
           p.setFname(rs1.getString(2));
           p.setSname(rs1.getString(3));
           p.setOpd(rs1.getString(4));
           pList.add(p);
        }
        return pList;
        }
}
    
    

