public class Personaje {
    private String nombre;
    private int edad;
    private String genero;
    private Pokemon pokemonInicial;

   
    public Personaje(String nombre, int edad, String genero, Pokemon pokemonInicial) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.pokemonInicial = pokemonInicial;
    }

   
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public Pokemon getPokemonInicial() {
        return pokemonInicial;
    }

    // Setters por si quieres modificar los valores más adelante
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPokemonInicial(Pokemon pokemonInicial) {
        this.pokemonInicial = pokemonInicial;
    }
}
