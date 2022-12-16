import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSetMetaData;

public class Persistencia {
    private Connection cn;
    private ResultSet rs; //Recibe los datos que pueda traer la BBDD.
    private PreparedStatement ps;//Recibe la orden del SELECT o lo que sea de la consulta.
    private ResultSetMetaData rsn;
    public String servidor;
    public String basededatos;
    public String usuario, clave, ejecutar;
    
    public Connection conectarse(){
        
        try{
             Class.forName("com.mysql.jdbc.Driver");//Determina el driver para conexion SQL.
        
            servidor = "localhost:3306/"; 
            basededatos = "proyectofinal";
            usuario = "root";
            clave = "";
            cn = (Connection)DriverManager.getConnection("jdbc:mysql://"+servidor+basededatos+"?autoReconnect=true&useSSL=false",
                usuario,clave);
            
        }catch(ClassNotFoundException ex){
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {      
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return cn;
    }
    
    
    ///////////////////////////////////////
    //ESTE METODO PERMITE HACER UNA CONSULTA A LA BBDD.
    //NOS RETORNA LA CONSULTA.
    public ResultSet consultaSQL(String busqueda){
        try {
            ps = conectarse().prepareStatement(busqueda);
            rs =(ResultSet) ps.executeQuery();
            rsn = rs.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return rs;
    }    
}
