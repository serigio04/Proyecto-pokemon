package main.java.TipoPokemon;

public class PokemonAgua extends Pokemon{
    
    public PokemonAgua (int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel){
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    public PokemonAgua(int numPokedex, String nombre, Object object, int vida, int experiencia, int nivel) {
        //TODO Auto-generated constructor stub
    }

    public void ataqueAgua(double vida){
        this.vida -= 2;
        System.out.println( this.nombre + " ha recibido un ataque");
    }
}
