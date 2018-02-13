/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;

public class Test extends JFrame {
    
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private DefaultTableModel jModel1;
    
    Test(){
    initi();
    dbtable();
    }
   
  
   
   private void dbtable() {
      jModel1.addColumn("id");    //add collmn name to model
      jModel1.addColumn("name");   //add collmn name to model 
      jModel1.addColumn("location");  //add collmn name to model
  
   Connection conn = null;
   Statement stmt = null;
   try{
      
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT * FROM testtable";
      ResultSet rs = stmt.executeQuery(sql);

      
      
     
      while(rs.next()){
         //Retrieve by column name
         int id  = rs.getInt("id");
         String name = rs.getString("name");
         String location = rs.getString("location");
       jModel1.addRow(new Object[]{Integer.toString(id) , name,location}); //adding model
      
      }
   
     
      rs.close();
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }    catch (ClassNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
   
  
   
}
   
   private void initi() 
   {

        jScrollPane1 = new JScrollPane();
        jModel1 = new DefaultTableModel(); 
        jTable1 = new JTable(jModel1); // model sent to table constractor as parameter
        // when model will be updated ... then table also be updated
        setSize(800,600);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

   
        jScrollPane1.setViewportView(jTable1);
        add(jScrollPane1);

    }                      

   
     public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

 
   
}
