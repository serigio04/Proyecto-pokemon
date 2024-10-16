package main.java.Proyecto;

public class Mochila {
    // Atributos
    private int espacio;
    private int pokebolas;
    private int pociones;
    private int caramelos;
    private boolean estado;

    // Constructor
    public Mochila(int espacio, int pokebolas, int pociones, int caramelos) {
        this.espacio = espacio;
        this.pokebolas = pokebolas;
        this.pociones = pociones;
        this.caramelos = caramelos;
        this.estado = true; // Por defecto, la mochila está activa
    }

    // Métodos get
    public int getEspacio() {
        return espacio;
    }

    public int getPokebolas() {
        return pokebolas;
    }

    public int getPociones() {
        return pociones;
    }

    public int getCaramelos() {
        return caramelos;
    }

    // Métodos set
    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    public void setPokebolas(int pokebolas) {
        this.pokebolas = pokebolas;
    }

    public void setPociones(int pociones) {
        this.pociones = pociones;
    }

    public void setCaramelos(int caramelos) {
        this.caramelos = caramelos;
    }

    // Método para mostrar el contenido de la mochila
    public void mostrarContenido() {
        System.out.println("Contenido de la mochila:");
        System.out.println("Espacio: " + espacio);
        System.out.println("Pokebolas: " + pokebolas);
        System.out.println("Pociones: " + pociones);
        System.out.println("Caramelos: " + caramelos);
    }

    // Método para verificar el estado de la mochila
    public boolean isEstado() {
        return estado;
    }

    // Método para cambiar el estado de la mochila
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
