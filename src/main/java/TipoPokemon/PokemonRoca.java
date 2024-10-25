package main.java.TipoPokemon;

public class PokemonRoca extends Pokemon {

    public PokemonRoca(int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel) {
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    // Método especial de ataque de roca
    public void ataqueRoca(Pokemon rival) {
        double danio = rival.getVida() / 3;
        this.atacar(rival, danio);  // Reducir vida del rival
        System.out.println(this.nombre + " ha usado ataque roca sobre " + rival.getNombre());
    }
}
