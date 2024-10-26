package main.java.Proyecto;

import java.util.ArrayList;
import main.java.TipoPokemon.Pokemon;
import main.java.TipoPokemon.PokemonAgua;
import main.java.TipoPokemon.PokemonElectrico;
import main.java.TipoPokemon.PokemonFuego;
import main.java.TipoPokemon.PokemonPlanta;
import main.java.TipoPokemon.PokemonPsiquico;

public class Rival extends Entrenador{
    private static ArrayList<Pokemon> pokemonesRival = new ArrayList<>();

    public Rival(int id, String nombre, int edad, String genero) {
        super(id, nombre, edad, genero);
    }

    public Rival() {
        super();
    }

    public static ArrayList<Pokemon> getPokemonsRival() {
        return pokemonesRival;
    }

    public static Pokemon ajustarPokemonSegunNivel(Pokemon pokemon) {
        int nivelPokemon = pokemon.getNivel();
        
        int grupoNivel = (nivelPokemon - 1) / 5;
        Pokemon pokemonRival;
    
        switch (grupoNivel) {
            case 0:  // Niveles 1-5
                pokemonRival = new PokemonElectrico(25, "Pikachu", 0, 40, 50, 5);
                break;
    
            case 1:  // Niveles 6-10
                pokemonRival = new PokemonFuego(5, "Charmeleon", 0, 60, 70, 10);
                break;
    
            case 2:  // Niveles 11-15
                pokemonRival = new PokemonFuego(6, "Charizard", 0, 80, 90, 15);
                break;
    
            case 3:  // Niveles 16-20
                pokemonRival = new PokemonElectrico(26, "Raichu", 0, 88, 98, 18);
                break;
    
            case 4:  // Niveles 21 en adelante
                pokemonRival = new PokemonPsiquico(150, "Mewtwo", 0, 250, 160, 30);
                break;
    
            default:
                System.out.println("Nivel no soportado, se utilizará un Pokémon de nivel alto por defecto.");
                pokemonRival = new PokemonAgua(130, "Gyarados", 0, 100, 50, 50);  // Valor por defecto
                break;
        }
        return pokemonRival;  // Devolver solo el Pokémon del rival
    }

    public ArrayList<Pokemon> getPokemonesRival() {
        return pokemonesRival;
    }
}
