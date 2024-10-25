package main.java.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Entrenador {
    private static int contador = 0;
    private String nombre;
    private int edad;
    private String genero;
    private int id; // ID generado por la base de datos

    private static ArrayList<Entrenador> entrenadores = new ArrayList<>();

    public Entrenador(String nombre, int edad, String genero) {
        this.id = ++contador;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public Entrenador(int id, String nombre, int edad, String genero) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
    }

    public Entrenador(){}

// getters

    public int getId(){
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

//setters

    public void setId(int id){
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void guardar(Connection conexion) {
        if (conexion != null) {
            String sql = "INSERT INTO entrenador (nombre, edad, genero) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, this.nombre);
                pstmt.setInt(2, this.edad);
                pstmt.setString(3, this.genero);
                
                // Ejecutar la inserción
                pstmt.executeUpdate();
                
                // Obtener el ID generado
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    this.id = generatedKeys.getInt(1); // Guardar el ID en el objeto entrenador
                }
                
                System.out.println("Entrenador " + this.nombre + " ha sido guardado exitosamente con ID: " + this.id);

                entrenadores.add(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay conexión con la base de datos.");
        }
    }

    public static ArrayList<Entrenador> obtenerEntrenadores() {
        return entrenadores;
    }
}
