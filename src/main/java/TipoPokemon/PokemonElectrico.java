package main.java.TipoPokemon;

public class PokemonElectrico extends Pokemon{
        
    public PokemonElectrico (int numPokedex, String nombre, int entrenador, double vida, double experiencia, int nivel){
        super(numPokedex, entrenador, nombre, vida, experiencia, nivel);
    }

    public void ataqueElectrico (Pokemon rival){
        double danio = rival.getVida() / 3;
        this.atacar(rival, danio);  // Reducir vida del rival
        System.out.println(this.nombre + " ha usado ataque electrico sobre " + rival.getNombre());
    }
}
