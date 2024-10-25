package main.java.TipoPokemon;

public class PokemonFantasma extends Pokemon {

    public PokemonFantasma(int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel) {
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    // Método especial de ataque fantasma
    public void ataqueFantasma(Pokemon rival) {
        double danio = rival.getVida() / 3;
        this.atacar(rival, danio);  // Reducir vida del rival
        System.out.println(this.nombre + " ha usado ataque fantasma sobre " + rival.getNombre());
        System.out.println("BUuUuUuuuU");
    }
}
