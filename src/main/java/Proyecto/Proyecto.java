package main.java.Proyecto;
import java.sql.Connection;
import java.util.Scanner;
import database.Conection.ConectionDB;
import main.java.Proyecto.Pokedex;
import main.java.TipoPokemon.Pokemon;
import main.java.TipoPokemon.PokemonAgua;
import main.java.TipoPokemon.PokemonFuego;
import main.java.TipoPokemon.PokemonPlanta;
import main.java.Proyecto.Entrenador;

public class Proyecto {
    static Pokemon pokemonInicial;
    static Entrenador entrenador;
    Pokedex pokedex;
    static boolean inicio = true;
    
    public static void limpiarPantalla(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else{
                System.out.println("\033[H\033[2J");
                System.out.flush();
            }
        }catch(Exception e){
            System.out.println("Error al limpiar pantalla");
        }
    }

    public static void esperar(int segundos){
        try{
            Thread.sleep(segundos * 1000);
        }catch(InterruptedException e){
            System.out.println("Algo salio mal y no se puede esperar");
        }
    }
    public static void main(String[] args) {
        //Conexion a la Data Base
        Connection conexion = ConectionDB.conectar();

        Scanner scanner = new Scanner(System.in);
        String genero;
        String nombre;
        int edad;
        int eleccion;

        System.out.println(conexion);

        do{
            System.out.println("    Pokemon     ");
            esperar(1);
            limpiarPantalla();

            //Elegi rjugador
            System.out.println("Escoge tu jugador \n Ingresa 'h' para hombre y 'm' para mujer");
            genero = scanner.nextLine();
            limpiarPantalla();
            esperar(1);

            //Nombre y edad
            System.out.println("Ingresa tu nombre");
            nombre = scanner.nextLine();
            System.out.println("Ingresa tu edad");
            edad = scanner.nextInt();
            entrenador = new Entrenador(nombre, edad, genero);
            entrenador.guardarEnBD(conexion);
            limpiarPantalla();
            esperar(1);

            //Pokemon inicial
            System.out.println("Elige tu pokemon inicial \n 1. Bulbasaur \n 2. Charmander \n 3. Squirtle");
            eleccion = scanner.nextInt();
            switch (eleccion) {
                case 1:
                    pokemonInicial = new PokemonPlanta(1, "Bulbasaur", entrenador, 10, 0, 1);
                    System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu pokedex");
                    pokemonInicial.agregarPokemonEnBD(conexion);
                    break;
                case 2:
                    pokemonInicial = new PokemonFuego(4, "Charmander", entrenador, 10, 0, 1);
                    pokemonInicial.agregarPokemonEnBD(conexion);
                    System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu pokedex");
                    break;
                case 3:
                    pokemonInicial = new PokemonAgua(7, "Squirtle", entrenador, 10, 0, 1);
                    pokemonInicial.agregarPokemonEnBD(conexion);
                    System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu pokedex");
                    break;
                default:
                    break;
            }
            limpiarPantalla();
            inicio = false;
        }
        while (inicio);
        scanner.close();
    }
}
