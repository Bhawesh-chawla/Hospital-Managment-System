/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.dao;

import Sanjeevaniapp.dbutil.DBConnection;
import Sanjeevaniapp.pojo.DoctorPojo;
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
public class DoctorDao {
     public static ArrayList<String> getAllDoctorsId() throws SQLException{
        ArrayList<String> docId=new ArrayList<>();
        ResultSet rs=DBConnection.getConnetion().createStatement().executeQuery("select doctorid from doctors where active='Y'");
        while(rs.next()){
            docId.add(rs.getString(1));
        }
        return docId;
    }
  public static ArrayList<DoctorPojo> getAllDoc() throws SQLException{
        Statement st=DBConnection.getConnetion().createStatement();
        ResultSet rs=st.executeQuery("select USERID,DOCTORID,QUALIFICATION,SPECIALIST from doctors where ACTIVE='Y'");
        ArrayList<DoctorPojo> DocList=new ArrayList<>();
        while(rs.next()){
            DoctorPojo D=new DoctorPojo();
            D.setUserid(rs.getString(1));
            D.setDocid(rs.getString(2));
            D.setQualification(rs.getString(3));
            D.setSpecilist(rs.getString(4));
            DocList.add(D);
         }
        return DocList;
    }
  public static HashMap<String,String> loadDocName() throws SQLException{
   Statement st=DBConnection.getConnetion().createStatement();
   ResultSet rs=st.executeQuery("select EMPID,EMPNAME from employees where ROLE='DOCTOR'and empid not in (select empid from users where usertype='DOCTOR')");
   HashMap<String,String> docData=new HashMap();
   while(rs.next()){
      String id=rs.getString(1);
      String ename=rs.getString(2);
      docData.put(id,ename);
   }
   return docData;
  }
  public static boolean registerDoctor(UserDetail user) throws SQLException{
       PreparedStatement ps=DBConnection.getConnetion().prepareStatement("insert into users values(?,?,?,?,?)");
       ps.setString(1, user.getUserid());
       ps.setString(2, user.getUserName());
       ps.setString(3, user.getEmpId());
       ps.setString(4, user.getPassword());
       ps.setString(5, user.getUserType());
       int x=ps.executeUpdate();
       return x>0;
    }  

public static String getNewDocID() throws SQLException{
        Statement s=DBConnection.getConnetion().createStatement();
        ResultSet rs=s.executeQuery("Select max(DOCTORID) from doctors");
        int id=1;
        rs.next();
        String docid=rs.getString(1);
        int eno=Integer.parseInt(docid.substring(3));
        id =id+eno;
        String sr="DOC"+id;
        System.out.println(sr);
        return sr;
        }
 public static boolean addDoc(DoctorPojo d) throws SQLException{
        PreparedStatement ps=DBConnection.getConnetion().prepareStatement("insert into doctors values (?,?,?,?,'Y')");
        ps.setString(1,d.getUserid());
        ps.setString(2,d.getDocid());
        ps.setString(3,d.getQualification());
        ps.setString(4,d.getSpecilist());
        int x =ps.executeUpdate();
        return x==1;
        }
 public static boolean deleteDoc(DoctorPojo D) throws SQLException{
     PreparedStatement ps=DBConnection.getConnetion().prepareStatement("update doctors set ACTIVE='N' where DOCTORID=?");
     ps.setString(1,D.getDocid());
    int result=ps.executeUpdate();
    return result==1;
 }
}
