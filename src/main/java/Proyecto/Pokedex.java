package main.java.Proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.Conection.ConectionDB;
import main.java.TipoPokemon.Pokemon;


public class Pokedex {
    private int numeroPokedex;
    private String nombre;
    private String descripcion;
    Pokemon pokemon;

    public Pokedex(){}

    public Pokedex(Pokemon pokemon, String descripcion){
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon(){
        return pokemon;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
// metodos
    public void agregarAPokedex(Connection conexion){
        if(conexion != null){
            String sql = "insert into pokemon (numeroPokedex, entrenador, nombre, tipo, nivel, vida, experiencia) values (?,?,?,?,?,?,?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)){
                pstmt.setInt(1, pokemon.getNumPokedex());
                pstmt.setInt(2, pokemon.getEntrenadorId());
                pstmt.setString(3, pokemon.getNombre());
                pstmt.setString(4, pokemon.getClass().getSimpleName());
                pstmt.setInt(5, pokemon.getNivel());
                pstmt.setDouble(6, pokemon.getVida());
                pstmt.setDouble(7, pokemon.getExperiencia());

                pstmt.executeUpdate();
                System.out.println("tu pokemon "+pokemon.getNombre()+" ha sido agregado exitosamente");
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("No hay conexión con la base de datos");
        }
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
            }
        }else{
            System.out.println("La conexión a la base de datos es nula.");
        }
    }
}
