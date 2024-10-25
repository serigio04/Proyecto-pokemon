package main.java.TipoPokemon;

public class PokemonPsiquico extends Pokemon {

    public PokemonPsiquico(int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel) {
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    // Método especial de ataque psíquico
    public void ataquePsiquico(Pokemon rival) {
        double danio = rival.getVida() / 3;
        this.atacar(rival, danio);  // Reducir vida del rival
        System.out.println(this.nombre + " ha usado ataque psíquico sobre " + rival.getNombre());
    }
}

