package main.java.TipoPokemon;

public class PokemonHielo extends Pokemon {

    public PokemonHielo(int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel) {
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    // Método especial de ataque de hielo
    public void ataqueHielo(Pokemon rival) {
        double danio = rival.getVida() / 3;
        this.atacar(rival, danio);  // Reducir vida del rival
        System.out.println(this.nombre + " ha usado ataque hielo sobre " + rival.getNombre());
    }
}
