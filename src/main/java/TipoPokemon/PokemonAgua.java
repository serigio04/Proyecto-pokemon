package main.java.TipoPokemon;

public class PokemonAgua extends Pokemon{
    
    public PokemonAgua (int numPokedex, String nombre, Entrenador entrenador, double vida, double experiencia, int nivel){
        super(numPokedex, nombre, entrenador, vida, experiencia, nivel);
    }

    public void ataqueAgua(double vida){
        this.vida -= 2;
        System.out.println( this.nombre + " ha recibido un ataque");
    }
}
