package main.java.TipoPokemon;
import main.java.Proyecto.*;

public abstract class Pokemon {
    public int numPokedex;
    protected String nombre;
    protected double vida;
    protected double experiencia;
    protected int nivel;
    Entrenador entrenador;
    double danioBase = Math.random() * (5 - 1) + 1;
    double xpBase;

    public Pokemon (int numPokedex, Entrenador entrenador, String nombre, double vida, double experiencia, int nivel){
        this.numPokedex = numPokedex;
        this.entrenador = entrenador;
        this.nombre = nombre;
        this.vida = 10;
        this.experiencia = 0;
        this.nivel = 1;
    }

    public Pokemon (){}

// Métodos get
    public int getNumPokedex() {
        return numPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public double getVida() {
        return vida;
    }

    public double getExperiencia() {
        return experiencia;
    }

    public int getNivel() {
        return nivel;
    }

    // Métodos set
    public void setNumPokedex(int numPokedex) {
        this.numPokedex = numPokedex;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public void setExperiencia(double experiencia) {
        this.experiencia = experiencia;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
// metodos de la clases
    public void atacar(Pokemon rival){
        double critico = Math.random() < 0.1 ? 2 : 1;
        System.out.println(this.nombre + " ataca!");
        double danio = ((this.vida * 0.1) + danioBase) * (nivel * 0.5) * critico;
        if (critico == 2){
            System.out.println(this.nombre + " ha hecho un golpe critico");
        }
        rival.recibirDanio(danio);
    }

    public void recibirDanio(double danio) {
        this.vida -= danio;
        System.out.println(this.nombre + " ha recibido " + danio + " puntos de ataque");
        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(this.nombre + " ha sido derrotado.");
        } else {
            System.out.println(this.nombre + " ahora tiene " + this.vida + " puntos de vida.");
        }
    }

    public void curarse(){
        this.vida += vida*1.2;
    }

    public void huir(){
        System.out.println("Tu y " + this.nombre + " han huido. \n La batalla ha terminado.");
    }

    public void ganarExperiencia(){
        this.experiencia += vida/3; 
    }

    public void subirNivel(){
        this.nivel++;
        this.vida = vida+vida/2.5;
        xpBase = 15;
        this.experiencia = experiencia + xpBase;
    }

    public void evolucionar(){
        if(this.nivel==16){
            System.out.println(this.nombre + " quiere evolucionar");
        }else{
            if(this.nivel==35){
                System.out.println(this.nombre + " quiere evolucionar");
            }
        }
    }
}