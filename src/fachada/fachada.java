/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Mary
 */
public class fachada extends baseDatos.database{
    
     public fachada(){      
    }
     
    
    public void insertarCliente(String dniCliente , String nombreCliente,String apellidosCliente,int telefonoCliente){
        
        String q="INSERT INTO cliente VALUES (null, '"+dniCliente+"', '"+nombreCliente+"', '"+apellidosCliente+"', '"+telefonoCliente+"');";
           System.out.println(q);
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
        
    }  
    
    public void insertarCoche(String matricula, String marca, String modelo, String color){
        
        String q="INSERT INTO coche VALUES (null, '"+matricula+"', '"+marca+"', '"+modelo+"', '"+color+"');";
           System.out.println(q);
         try{
             //PreparedStatement pstm = this.getConexion().prepareStatement(q);
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
        
    }  
    
    public void insertarClienteTieneCoche(String dniCliente, String matricula){
        
        String q="insert into clienteTieneCoche values ((select idCliente from cliente where dniCliente='"+dniCliente+"'), (select idCoche from coche where matricula='"+matricula+"'));";
           System.out.println(q);
         try{
             //PreparedStatement pstm = this.getConexion().prepareStatement(q);
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
        
    }  
    
    public void insertarClienteTieneCocheConId(int idCliente, String matricula){
        
        String q="insert into clienteTieneCoche values ('"+idCliente+"', (select idCoche from coche where matricula='"+matricula+"'));";
           System.out.println(q);
         try{
             //PreparedStatement pstm = this.getConexion().prepareStatement(q);
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
        
    }  
    
    public DefaultTableModel getTablaCliente(){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"idCliente", "dniCliente", "nombreCliente", "apellidosCliente", "telefonoCliente"};
          try{
              String sql ="select count(*) as total from cliente";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql);
              ResultSet res = pstm.executeQuery();
              res.next();
              registros = res.getInt("total");
              res.close();
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
          // se crea una matriz con tanta filas y columnas como se necesiten
          Object[][] data = new String[registros][5];
            try {
              //se realiza la consulta sql y llenamos los datos en la matriz "Object[][]" data
              String sql2 = "select idCliente, dniCliente, nombreCliente, apellidosCliente, telefonoCliente from cliente";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql2);
              ResultSet res = pstm.executeQuery();
              int i = 0;
              while(res.next()){
                  data[i][0] = res.getString("idCliente");
                  data[i][1] = res.getString("dniCliente");
                  data[i][2] = res.getString("nombreCliente");
                  data[i][3] = res.getString("apellidosCliente");
                  data[i][4] = res.getString("telefonoCliente");
                i++;
              }
              res.close();
              //se añade la matriz de datos en el DefaultTableModel
              tablemodel.setDataVector(data, columNames);
                System.out.println("tabla cliente cargada");
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
                    return tablemodel;
        
        
      }
    
}
