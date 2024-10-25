package main.java.Proyecto;

import java.util.ArrayList;
import main.java.TipoPokemon.Pokemon;
import main.java.TipoPokemon.PokemonAgua;
import main.java.TipoPokemon.PokemonElectrico;
import main.java.TipoPokemon.PokemonFuego;
import main.java.TipoPokemon.PokemonPlanta;
import main.java.TipoPokemon.PokemonPsiquico;

public class Rival extends Entrenador{
    private ArrayList<Pokemon> pokemonesRival;

    public Rival(int id, String nombre, int edad, String genero) {
        super(id, nombre, edad, genero);
        this.pokemonesRival = new ArrayList<>();
    }

    public Rival() {
        super();
        this.pokemonesRival = new ArrayList<>();
    }

    public void ajustarPokemonSegunNivel(Pokemon pokemon) {
        int nivelPokemon = pokemon.getNivel();
        Entrenador entrenador;

        // Agrupar los niveles de 5 en 5
    int grupoNivel = (nivelPokemon - 1) / 5;

    switch (grupoNivel) {
        case 0:  // Niveles 1-5
            this.pokemonesRival.add(new PokemonElectrico(25, "Pikachu", 0, 40, 50, 5));
            break;

        case 1:  // Niveles 6-10
            this.pokemonesRival.add(new PokemonFuego(5, "Charmeleon", 0, 60, 70, 10));
            this.pokemonesRival.add(new PokemonAgua(7, "Wartortle", 0, 55, 65, 9));
            break;

        case 2:  // Niveles 11-15
            this.pokemonesRival.add(new PokemonFuego(6, "Charizard", 0, 80, 90, 15));
            this.pokemonesRival.add(new PokemonAgua(8, "Blastoise", 0, 85, 95, 15));
            this.pokemonesRival.add(new PokemonPlanta(3, "Venusaur", 0, 82, 92, 14));
            break;

        case 3:  // Niveles 16-20
            this.pokemonesRival.add(new PokemonFuego(6, "Charizard", 0, 90, 100, 20));
            this.pokemonesRival.add(new PokemonAgua(8, "Blastoise", 0, 95, 105, 20));
            this.pokemonesRival.add(new PokemonPlanta(3, "Venusaur", 0, 92, 102, 19));
            this.pokemonesRival.add(new PokemonElectrico(26, "Raichu", 0, 88, 98, 18));
            break;

        case 4:  // Niveles 21 en adelante
            this.pokemonesRival.add(new PokemonFuego(6, "Charizard", 0, 100, 110, 25));
            this.pokemonesRival.add(new PokemonAgua(8, "Blastoise", 0, 105, 115, 25));
            this.pokemonesRival.add(new PokemonPlanta(3, "Venusaur", 0, 102, 112, 24));
            this.pokemonesRival.add(new PokemonElectrico(26, "Raichu", 0, 98, 108, 23));
            this.pokemonesRival.add(new PokemonPsiquico(150, "Mewtwo", 0, 150, 160, 30));
            break;

        default:
            System.out.println("Nivel no soportado, se utilizarán Pokémon de nivel alto por defecto.");
            this.pokemonesRival.add(new PokemonAgua(130, "Gyarados", 0, 100, 50, 50));  // Valor por defecto
            break;
        }
    }

    public ArrayList<Pokemon> getPokemonesRival() {
        return pokemonesRival;
    }
}
