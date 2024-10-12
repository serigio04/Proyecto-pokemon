package main.java.Proyecto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import database.Conection.ConectionDB;


public class Pokedex {
    private int numeroPokedex;
    private String nombre;
    private String descripcion;

    public Pokedex(int numeroPokedex, String nombre, String descripcion){
        this.numeroPokedex = numeroPokedex;
        this.nombre =  nombre;
        this.descripcion = descripcion;
    }

    public int getNumPokedex(){
        return numeroPokedex;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setNumPokedex(int numeroPokedex){
        this.numeroPokedex = numeroPokedex;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
// metodos
    public void agregarAPokedex(){
        //this.
    }

    public void verPokemon(){
        System.out.println("El pokemon " + this.nombre + " \n Tiene el numero en la pokedex " + this.numeroPokedex + " \n Sobre el pokemon: \n " + this.descripcion);
    }
    public static void listarPokemon() {
        Connection conexion = ConectionDB.conectar();

        if (conexion != null) {
            try {
                // Crear un objeto Statement
                Statement stmt = conexion.createStatement();

                // Consulta SQL para obtener todos los Pokémon
                String sql = "SELECT * FROM pokedex";

                // Ejecutar la consulta y obtener los resultados
                ResultSet rs = stmt.executeQuery(sql);

                // Recorrer los resultados
                System.out.println("Listado de Pokémon en la Pokédex:");
                while (rs.next()) {
                    int id = rs.getInt("numeroPokedex");
                    String nombre = rs.getString("nombre");
                    String tipo = rs.getString("tipo");
                    int descripcion = rs.getInt("descripcion");

                    // Mostrar los detalles de cada Pokémon
                    System.out.println("Numero Pokedex: " + id + ", Nombre: " + nombre + ", Tipo: " + tipo + ", Descripción: " + descripcion);
                }

                // Cerrar ResultSet y Statement
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // Cerrar la conexión
                    if (conexion != null && !conexion.isClosed()) {
                        conexion.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
