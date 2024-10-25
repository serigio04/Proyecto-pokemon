package main.java.Proyecto;

import database.Conection.ConectionDB;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import main.java.TipoPokemon.Pokemon;
import main.java.TipoPokemon.PokemonAgua;
import main.java.TipoPokemon.PokemonFuego;
import main.java.TipoPokemon.PokemonPlanta;

public class Proyecto {
    static Pokemon pokemonInicial;
    static Entrenador entrenador;
    static boolean inicio = true;

    public static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar pantalla");
        }
    }

    public static void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            System.out.println("Algo salió mal y no se puede esperar");
        }
    }

    public static void main(String[] args) {
        // Conexión a la base de datos
        Connection conexion = ConectionDB.conectar();
        Scanner scanner = new Scanner(System.in);
        String genero;
        String nombre;
        int edad;
        int eleccion;

        System.out.println(conexion);

        do {
            System.out.println("    Menú Principal de Pokémon    ");
            System.out.println("1. Escoger Pokémon inicial");
            System.out.println("2. Ver Pokédex");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Elección del jugador
                    System.out.println("Escoge tu jugador\n Ingresa 'h' para hombre y 'm' para mujer");
                    genero = scanner.nextLine();
                    limpiarPantalla();
                    esperar(1);

                    // Nombre y edad
                    System.out.println("Ingresa tu nombre");
                    nombre = scanner.nextLine();
                    System.out.println("Ingresa tu edad");
                    edad = scanner.nextInt();
                    entrenador = new Entrenador(nombre, edad, genero);
                    entrenador.guardar(conexion);
                    int entrenadorId = entrenador.getId();

                    limpiarPantalla();
                    esperar(1);

                    // Pokémon inicial
                    System.out.println("Elige tu Pokémon inicial\n 1. Bulbasaur\n 2. Charmander\n 3. Squirtle");
                    eleccion = scanner.nextInt();
                    switch (eleccion) {
                        case 1:
                            pokemonInicial = new PokemonPlanta(1, "Bulbasaur", entrenadorId, 10, 0, 1);
                            System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu Pokédex");
                            pokemonInicial.agregarPokemon(conexion);
                            break;
                        case 2:
                            pokemonInicial = new PokemonFuego(4, "Charmander", entrenadorId, 10, 0, 1);
                            pokemonInicial.agregarPokemon(conexion);
                            System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu Pokédex");
                            break;
                        case 3:
                            pokemonInicial = new PokemonAgua(7, "Squirtle", entrenadorId, 10, 0, 1);
                            pokemonInicial.agregarPokemon(conexion);
                            System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu Pokédex");
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    limpiarPantalla();
                    break;
                case 2:
                    // Mostrar Pokédex
                    mostrarPokedex();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    inicio = false;
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (inicio);

        scanner.close();
    }

    public static void mostrarPokedex() {
        System.out.println("----- Pokédex -----");
        ArrayList<Pokemon> pokemons = Pokemon.getPokemons();
        if (pokemons.isEmpty()) {
            System.out.println("Aún no tienes Pokémon en tu Pokédex.");
        } else {
            for (Pokemon pokemon : pokemons) {
                System.out.println("Nombre: " + pokemon.getNombre());
                System.out.println("Nivel: " + pokemon.getNivel());
                System.out.println("Vida: " + pokemon.getVida());
                System.out.println("Experiencia: " + pokemon.getExperiencia());
                System.out.println("------------------");
            }
        }
    }
}
