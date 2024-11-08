/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iug;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class persistencia {
 
    Connection conexion;
    String url="jdbc:mysql://localhost:3306/java";
    String usuario="root";
    String clave="santiago";
    String Drive="com.mysql.cj.jdbc.Driver";
    
    
            
        


public persistencia() {
        try{
            Class.forName(Drive);
            //Modulo de conexion
            conexion=DriverManager.getConnection(url,usuario,clave);
            System.out.println("Conexion Exitosa");
            
        }catch(ClassNotFoundException e){
            System.out.println("Error al cargar el drive de MySQL"+e.getMessage());
           }catch(SQLException e){
               System.out.println("Error al conectar la Base de Datos"+e.getMessage());
           }
    }


    public Connection getConexion(){
        return conexion;
    }

    
     public void CerrarConexion(){
        try{
            if(conexion !=null && !conexion.isClosed()){
                conexion.close();
                System.out.println("Se a Cerrado la conexion");
            }
        }catch(SQLException e){
            System.out.println("Error al cerrar la conexion"+e.getMessage());
        }
    }
     
    public String[] ejecutarConsulta(String consulta){
         String[] result=new String[4];
        try{
            Statement stmt=conexion.createStatement();
            ResultSet rs=stmt.executeQuery(consulta);
            
            while(rs.next()){
                //Aqui coloca el nombre de las columnas que tiene en MySQL
                
                
                result[0]="id: "+rs.getInt("id");
                result[1]="Nombre: "+rs.getString("nombre");
                result[2]="Identificacion: "+rs.getString("identificacion");
                result[3]="Contraseña: "+rs.getInt("contraseña");
               
            }
        }catch(SQLException e){
            System.out.println("Error al ejecutar la consulta "+e.getMessage());
        }
             return result;
    }
    public String[] login(String cons){
        String[] result=new String[2];
        try {
            Statement stm = conexion.createStatement();
            ResultSet rs = stm.executeQuery(cons);
            while(rs.next()){
                //Aqui coloca el nombre de las columnas que tiene en MySQL
                
                result[0]= String.valueOf( rs.getInt("identificacion"));
                result[1]="Contraseña de cuenta: "+rs.getString("contraseña");
            }
        } catch (SQLException e){
            System.out.println("Error en la consulta :"+e.getMessage());
        }
        return result;
    }
}
