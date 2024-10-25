package main.java.TipoPokemon;

public class PokemonVolador extends Pokemon {

    public PokemonVolador(int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel) {
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    // Método especial de ataque volador
    public void ataqueVolador(Pokemon rival) {
        double danio = rival.getVida() / 3;
        this.atacar(rival, danio);  // Reducir vida del rival
        System.out.println(this.nombre + " ha usado ataque volador sobre " + rival.getNombre());
    }
}

