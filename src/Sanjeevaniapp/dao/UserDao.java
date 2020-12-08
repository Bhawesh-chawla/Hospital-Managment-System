/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanjeevaniapp.dao;

import Sanjeevaniapp.dbutil.DBConnection;
import Sanjeevaniapp.pojo.userPojo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author HP
 */
public class UserDao {
    public static String validateUser(userPojo user) throws SQLException{
        String qry="Select username from users where userid=? and password=? and usertype=?";
        System.out.println(user);
        PreparedStatement ps=DBConnection.getConnetion().prepareStatement(qry);
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs=ps.executeQuery();
        String username=null;
        if(rs.next()){
            username=rs.getString(1);
        }
        return username;
    }
    public static boolean changePassword(String userId , String pwd) throws SQLException{
        PreparedStatement ps=DBConnection.getConnetion().prepareStatement("update users set password=? where userid=?");
        ps.setString(1, pwd);
        ps.setString(2, userId);
        return(ps.executeUpdate()!=0);
    }   
    
   public static HashMap<String,String> getReceptionist() throws SQLException{
       HashMap<String,String> RecpList=new HashMap();
       Statement st=DBConnection.getConnetion().createStatement();
       ResultSet rs=st.executeQuery("select userid,username from users where usertype='RECEPTIONIST'");
       while(rs.next()){
           RecpList.put(rs.getString(1),rs.getString(2));
       }
       return RecpList;
   }
    
}
