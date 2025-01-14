package database.Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDB {
    public static Connection conectar(){
        // aws String url = "jdbc:mysql://db-pokemon.chkuou2ciahg.us-east-2.rds.amazonaws.com:3306/pokemon_db";
        String url = "jdbc:mysql://127.0.0.1:3306/pokemon_db";
        // aws String usuario = "ash_ketchup"; 
        String usuario = "root"; 
        // aws String contrasena = "ProyectoPokemon24"; 
        String contrasena = "root"; 

        Connection conexion = null;

        try {
            // Registrar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            System.out.println("Conexión exitosa a la base de datos.");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver JDBC: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }

        return conexion;
    }
}