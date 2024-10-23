package main.java.TipoPokemon;

import main.java.Proyecto.Entrenador;

public class PokemonFuego extends Pokemon{

    public PokemonFuego  (int numPokedex, String nombre, Entrenador entrenador, double vida, double experiencia, int nivel){
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);

    }

    public void ataqueFuego (double vida){
        this.vida -= 2;
        System.out.println (this.nombre + "ha recibido un ataque");
    }

}
