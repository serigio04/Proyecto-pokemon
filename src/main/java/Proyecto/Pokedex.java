package main.java.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.Conection.ConectionDB;
import main.java.TipoPokemon.Pokemon;
import main.java.TipoPokemon.PokemonAgua;
import main.java.TipoPokemon.PokemonElectrico;
import main.java.TipoPokemon.PokemonFuego;
import main.java.TipoPokemon.PokemonPlanta;
import main.java.TipoPokemon.PokemonPsiquico;
import main.java.TipoPokemon.PokemonTierra;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class Pokedex {
    private int numeroPokedex;
    private String nombre;
    private String descripcion;
    Pokemon pokemon;

    private static List<Pokemon> pokemonSalvajes = new ArrayList<>();
    //lista pokemones salvajes
    static {
        System.out.println("Inicializando pokemones salvajes");
        pokemonSalvajes.add(new PokemonElectrico(25, "Pikachu", 541, 35, 0, 5));
        pokemonSalvajes.add(new PokemonFuego(4, "Charmander", 541, 39, 0, 5));
        pokemonSalvajes.add(new PokemonAgua(7, "Squirtle", 541, 44, 0, 5));
        pokemonSalvajes.add(new PokemonPlanta(1, "Bulbasaur", 541, 45, 0, 5));
        pokemonSalvajes.add(new PokemonFuego(5, "Charmeleon", 541, 58, 0, 10));
        pokemonSalvajes.add(new PokemonAgua(8, "Wartortle", 541, 59, 0, 10));
        pokemonSalvajes.add(new PokemonPlanta(2, "Ivysaur", 541, 60, 0, 10));
        pokemonSalvajes.add(new PokemonFuego(6, "Charizard", 541, 78, 0, 15));
        pokemonSalvajes.add(new PokemonAgua(9, "Blastoise", 541, 79, 0, 15));
        pokemonSalvajes.add(new PokemonPlanta(3, "Venusaur", 541, 80, 0, 15));
        pokemonSalvajes.add(new PokemonTierra(50, "Diglett", 541, 10, 0, 5));
        pokemonSalvajes.add(new PokemonTierra(51, "Dugtrio", 541, 35, 0, 10));
        pokemonSalvajes.add(new PokemonElectrico(26, "Raichu", 541, 60, 0, 15));
        pokemonSalvajes.add(new PokemonFuego(37, "Vulpix", 541, 38, 0, 5));
        pokemonSalvajes.add(new PokemonAgua(54, "Psyduck", 541, 50, 0, 5));
        pokemonSalvajes.add(new PokemonPlanta(43, "Oddish", 541, 45, 0, 5));
        pokemonSalvajes.add(new PokemonTierra(74, "Geodude", 541, 40, 0, 5));
        pokemonSalvajes.add(new PokemonAgua(60, "Poliwag", 541, 40, 0, 5));
        pokemonSalvajes.add(new PokemonElectrico(81, "Magnemite", 541, 25, 0, 5));
        pokemonSalvajes.add(new PokemonFuego(58, "Growlithe", 541, 55, 0, 5));
        pokemonSalvajes.add(new PokemonTierra(42, "Sandshrew", 541, 50, 0, 10));
        pokemonSalvajes.add(new PokemonFuego(10, "Ninetales", 541, 73, 0, 20));
        pokemonSalvajes.add(new PokemonAgua(12, "Gyarados", 541, 95, 0, 20));
        pokemonSalvajes.add(new PokemonAgua(13, "Kingler", 541, 90, 0, 20));
        pokemonSalvajes.add(new PokemonElectrico(20, "Jolteon", 541, 65, 0, 20));
        pokemonSalvajes.add(new PokemonFuego(22, "Flareon", 541, 65, 0, 20));
        pokemonSalvajes.add(new PokemonPlanta(21, "Exeggutor", 541, 95, 0, 25));
        pokemonSalvajes.add(new PokemonPsiquico(90, "Alakazam", 541, 55, 0, 15));
        pokemonSalvajes.add(new PokemonAgua(35, "Lapras", 541, 80, 0, 20));
        pokemonSalvajes.add(new PokemonPlanta(14, "Tangela", 541, 65, 0, 15));
        pokemonSalvajes.add(new PokemonElectrico(31, "Electabuzz", 541, 65, 0, 20));
        pokemonSalvajes.add(new PokemonFuego(30, "Arcanine", 541, 85, 0, 30));
        pokemonSalvajes.add(new PokemonPsiquico(92, "Mr. Mime", 541, 45, 0, 15));
        pokemonSalvajes.add(new PokemonElectrico(80, "Zapdos", 541, 100, 0, 50));
        pokemonSalvajes.add(new PokemonFuego(39, "Rapidash", 541, 65, 0, 15));
        pokemonSalvajes.add(new PokemonFuego(46, "Charizard", 541, 78, 0, 25));
        pokemonSalvajes.add(new PokemonAgua(44, "Poliwhirl", 541, 65, 0, 15));
        pokemonSalvajes.add(new PokemonAgua(9, "Seaking", 541, 80, 0, 25));
        pokemonSalvajes.add(new PokemonPlanta(15, "Bellossom", 541, 75, 0, 30));
        pokemonSalvajes.add(new PokemonTierra(63, "Marowak", 541, 60, 0, 20));
        pokemonSalvajes.add(new PokemonElectrico(24, "Pichu", 541, 35, 0, 5));
        pokemonSalvajes.add(new PokemonAgua(6, "Goldeen", 541, 45, 0, 5));
        pokemonSalvajes.add(new PokemonPlanta(25, "Chikorita", 541, 60, 0, 10));
        pokemonSalvajes.add(new PokemonAgua(64, "Blastoise", 541, 79, 0, 20));
        pokemonSalvajes.add(new PokemonFuego(67, "Ninetales", 541, 73, 0, 20));
        pokemonSalvajes.add(new PokemonAgua(75, "Kabutops", 541, 60, 0, 20));
        pokemonSalvajes.add(new PokemonPlanta(86, "Exeggutor", 541, 95, 0, 25));
        pokemonSalvajes.add(new PokemonElectrico(28, "Raichu", 541, 60, 0, 15));
        pokemonSalvajes.add(new PokemonAgua(56, "Poliwrath", 541, 85, 0, 30));
        pokemonSalvajes.add(new PokemonFuego(87, "Flareon", 541, 65, 0, 20));
    }

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

    public static void inicializarPokedex() {
        // Este método solo asegura que el bloque estático se ejecute.
    }

    public static Pokemon buscarPokemonSalvaje() {
        if (pokemonSalvajes.isEmpty()) {
            System.out.println("No hay Pokémon salvajes disponibles.");
            return null;
        }
        Random random = new Random();
        return pokemonSalvajes.get(random.nextInt(pokemonSalvajes.size()));
    }


}
