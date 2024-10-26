package main.java.TipoPokemon;

public class PokemonTierra extends Pokemon {

    public PokemonTierra(int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel) {
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    public PokemonTierra(int numPokedex, String nombre, Object object, int vida, int experiencia, int nivel) {
        //TODO Auto-generated constructor stub
    }

    // Método especial de ataque de roca
    public void ataqueTierra(Pokemon rival) {
        double danio = rival.getVida() / 3;
        this.atacar(rival, danio);  // Reducir vida del rival
        System.out.println(this.nombre + " ha usado ataque tierra sobre " + rival.getNombre());
    }
}