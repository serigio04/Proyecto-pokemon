package main.java.TipoPokemon;

public class PokemonFuego extends Pokemon{

    public PokemonFuego (int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel){
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    public void ataqueFuego (double vida){
        this.vida -= 2;
        System.out.println (this.nombre + "ha recibido un ataque");
    }

}
