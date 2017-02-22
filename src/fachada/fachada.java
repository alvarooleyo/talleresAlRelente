/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import baseDatos.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import vista.vista;


/**
 *
 * @author Mary
 */
public class fachada extends baseDatos.database{
    
     public fachada(){      
    }
     
     
    public void reporteCoches() throws SQLException, JRException{
    
        database cn = new database();
        String dir;
        dir = "src/reporte/reporte.jrxml";
        
        JasperReport reporteJasper = JasperCompileManager.compileReport(dir);
        JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, null, cn.getConexion());
        JasperViewer visor = new JasperViewer(mostrarReporte, false);
        visor.setVisible(true);
        
        
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
    
    
    public void modificarCliente(int idCliente, String dniCliente,String nombreCliente,String apellidosCliente, int telefonoCliente){
        String q="update empleado set dniCliente='"+dniCliente+"',nombreCliente='"+nombreCliente+"', apellidosCliente='"+apellidosCliente+"', telefonoCliente='"+telefonoCliente+"' where idCliente='"+idCliente+"'";
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
    
    
    public void eliminarCliente(int idCliente){
                  
        String q="delete from cliente where idCliente='"+idCliente+"' and (select count(*) from clienteTieneCoche where idCliente = '"+idCliente+"')='0'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null, "El cliente que intenta eliminar tiene coches asignados, por favor eliminelos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
    }
    
    
    public void eliminarClienteDeClienteTieneCoche(int idCliente){
        String q="delete from clienteTieneCoche where idCliente='"+idCliente+"'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 }
    }
    
    
    
    
    public void insertarCoche(String matricula, String marca, String modelo, String color){
        
        String q="INSERT INTO coche VALUES (null, '"+matricula+"', '"+marca+"', '"+modelo+"', '"+color+"', '0');";
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
    
    
    public void modificarCoche(int idCoche, String matricula,String marca,String modelo, String color, int enReparacion){
        String q="update coche set matricula='"+matricula+"',marca='"+marca+"', modelo='"+modelo+"', color='"+color+"', enReparacion='"+enReparacion+"' where idCoche='"+idCoche+"'";
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
    
    public void terminarEnReparacion(String idCoche){
        String q="update coche set enReparacion='0' where idCoche='"+idCoche+"'";
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
    
    public void comenzarEnReparacion(String idCoche){
        String q="update coche set enReparacion='1' where idCoche='"+idCoche+"'";
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
    
    
    public void eliminarCoche(int idCoche){
        String q="delete from coche where idCoche='"+idCoche+"' and enReparacion='0'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null, "Error: el coche que está intentando eliminar, se encuentra en proceso de reparacion");
                 System.err.println( e.getMessage() );
                 }
    }
    
    
    public void eliminarCocheDeClienteTieneCoche(int idCoche){
        String q="delete from clienteTieneCoche where idCoche='"+idCoche+"'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
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
    
    
    public void insertarEmpleado(String dniEmpleado, String nombreEmpleado, String apellidosEmpleado, int telefonoEmpleado){
        
        String q="insert into empleado values (null, '"+dniEmpleado+"', '"+nombreEmpleado+"', '"+apellidosEmpleado+"', '"+telefonoEmpleado+"')  ";
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
    
    
    public void modificarEmpleado(int idEmpleado, String dniEmpleado,String nombreEmpleado,String apellidosEmpleado, int telefonoEmpleado){
        String q="update empleado set dniEmpleado='"+dniEmpleado+"',nombreEmpleado='"+nombreEmpleado+"', apellidosEmpleado='"+apellidosEmpleado+"', telefonoEmpleado='"+telefonoEmpleado+"' where idEmpleado='"+idEmpleado+"'";
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
    
    
    public void eliminarEmpleado(int idEmpleado){
        String q="delete from empleado where idEmpleado='"+idEmpleado+"'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 }
    }
    
    
    public void insertarReparacion(String idEmpleado, String idCoche, String descripcion){
        
        String q="INSERT INTO reparacion VALUES (null, '"+idEmpleado+"', '"+idCoche+"', '"+descripcion+"');";
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
    
    public void eliminarReparacion(int idReparacion){
        String q="delete from reparacion where idReparacion='"+idReparacion+"'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
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
    
    
    
    
    public DefaultTableModel getTablaEmpleado(){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"idEmpleado", "dniEmpleado", "nombreEmpleado", "apellidosEmpleado", "telefonoEmpleado"};
          try{
              String sql ="select count(*) as total from empleado";
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
              String sql2 = "select idEmpleado, dniEmpleado, nombreEmpleado, apellidosEmpleado, telefonoEmpleado from empleado";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql2);
              ResultSet res = pstm.executeQuery();
              int i = 0;
              while(res.next()){
                  data[i][0] = res.getString("idEmpleado");
                  data[i][1] = res.getString("dniEmpleado");
                  data[i][2] = res.getString("nombreEmpleado");
                  data[i][3] = res.getString("apellidosEmpleado");
                  data[i][4] = res.getString("telefonoEmpleado");
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
    
    
    
    
    
    public DefaultTableModel getTablaReparacion(){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"idReparacion", "idEmpleado", "idCoche", "descripcion"};
          try{
              String sql ="select count(*) as total from reparacion";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql);
              ResultSet res = pstm.executeQuery();
              res.next();
              registros = res.getInt("total");
              res.close();
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
          // se crea una matriz con tanta filas y columnas como se necesiten
          Object[][] data = new String[registros][4];
            try {
              //se realiza la consulta sql y llenamos los datos en la matriz "Object[][]" data
              String sql2 = "select idReparacion, idEmpleado, idCoche, descripcion from reparacion";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql2);
              ResultSet res = pstm.executeQuery();
              int i = 0;
              while(res.next()){
                  data[i][0] = res.getString("idReparacion");
                  data[i][1] = res.getString("idEmpleado");
                  data[i][2] = res.getString("idCoche");
                  data[i][3] = res.getString("descripcion");
                 
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
    
    
    
    
    public DefaultTableModel getTablaClienteConId(String buscar, String texto){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"idCliente", "dniCliente", "nombreCliente", "apellidosCliente", "telefonoCliente"};
          try{
              String sql ="select count(*) as total from cliente" ;
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
              String sql2 = "select idCliente, dniCliente, nombreCliente, apellidosCliente, telefonoCliente from cliente where "+buscar+"Cliente like '%"+texto+"%'  ";
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
    
    public String buscarcliente(String buscar, String texto){
        
           String z="select idCliente as id from cliente where "+buscar+"Cliente like '%"+texto+"%';";
                     System.out.println(z);
                     String id=null;

          try{
             PreparedStatement pstm = this.getConexion().prepareStatement(z);             
             ResultSet res=pstm.executeQuery();
             res.next();
             id=res.getString("id");
             res.close();
             pstm.close();
             
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 }
          return id;
      }
    
    public DefaultTableModel getTablaCoche(int id){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"idCoche", "matricula", "marca", "modelo", "color", "enReparacion"};
          try{
              String sql ="select count(*) as total from coche";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql);
              ResultSet res = pstm.executeQuery();
              res.next();
              registros = res.getInt("total");
              res.close();
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
          // se crea una matriz con tanta filas y columnas como se necesiten
          Object[][] data = new String[registros][6];
            try {
              //se realiza la consulta sql y llenamos los datos en la matriz "Object[][]" data
              
              
              
              String sql2 = "select idCoche, matricula, marca, modelo, color, enReparacion from coche where idCoche IN (select idCoche from clienteTieneCoche where idCliente = '"+id+"')";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql2);
              ResultSet res = pstm.executeQuery();
              int i = 0;
              while(res.next()){
                  data[i][0] = res.getString("idCoche");
                  data[i][1] = res.getString("matricula");
                  data[i][2] = res.getString("marca");
                  data[i][3] = res.getString("modelo");
                  data[i][4] = res.getString("color");
                  data[i][5] = res.getString("enReparacion");
                i++;
              }
              res.close();
              //se añade la matriz de datos en el DefaultTableModel
              tablemodel.setDataVector(data, columNames);
                System.out.println("tabla coche cargada");
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
                    return tablemodel;
        
        
      }
    
    
    public DefaultTableModel getTablaTodosCoches(){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"idCoche", "matricula", "marca", "modelo", "color", "enReparacion"};
          try{
              String sql ="select count(*) as total from coche";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql);
              ResultSet res = pstm.executeQuery();
              res.next();
              registros = res.getInt("total");
              res.close();
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
          // se crea una matriz con tanta filas y columnas como se necesiten
          Object[][] data = new String[registros][6];
            try {
              //se realiza la consulta sql y llenamos los datos en la matriz "Object[][]" data
              
              
              
              String sql2 = "select idCoche, matricula, marca, modelo, color, enReparacion from coche";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql2);
              ResultSet res = pstm.executeQuery();
              int i = 0;
              while(res.next()){
                  data[i][0] = res.getString("idCoche");
                  data[i][1] = res.getString("matricula");
                  data[i][2] = res.getString("marca");
                  data[i][3] = res.getString("modelo");
                  data[i][4] = res.getString("color");
                  data[i][5] = res.getString("enReparacion");
                i++;
              }
              res.close();
              //se añade la matriz de datos en el DefaultTableModel
              tablemodel.setDataVector(data, columNames);
                System.out.println("tabla coche cargada");
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
                    return tablemodel;
        
        
      }
    
    
    
    public DefaultComboBoxModel rellenarComboEmpleado(){
        
        DefaultComboBoxModel vector=new DefaultComboBoxModel();
         int total=0;
         /**Obtenemos la cantidad de elementos que contendra el ComboBox de comerciales**/
         try{
             //se arma la consulta
             PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM empleado");
             //se ejecuta la consulta
             ResultSet res1 = pstm.executeQuery();
             res1.next();
             total= res1.getInt("total");
             res1.close();
      }catch(SQLException e){
          System.err.println( e.getMessage() );
      }         
         int i=0;
         Object[] data = new String[total];       
         String q = "select idEmpleado FROM empleado" ;       
         try {
             //se arma la consulta
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            //se ejecuta la consulta
            ResultSet resultado=pstm.executeQuery();
            while(resultado.next()){
                data[i]=resultado.getString("idEmpleado");
                vector.addElement(data[i].toString());
                i++;
            }           
            pstm.close();
            resultado.close();            
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }         
        return vector;          
    }
    
}
