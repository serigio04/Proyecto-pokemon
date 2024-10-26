package main.java.Proyecto;

import database.Conection.ConectionDB;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;
import main.java.TipoPokemon.Pokemon;
import main.java.TipoPokemon.PokemonAgua;
import main.java.TipoPokemon.PokemonElectrico;
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
            System.out.println("3. Atrapar pokemon");
            System.out.println("4. Pelear contra rival");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Elección del jugador
                    limpiarPantalla();
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
                    System.out.println("Bienvenido "+entrenador.getNombre()+" elige tu Pokémon inicial\n 1. Bulbasaur\n 2. Charmander\n 3. Squirtle");
                    eleccion = scanner.nextInt();
                    switch (eleccion) {
                        case 1:
                            pokemonInicial = new PokemonPlanta(1, "Bulbasaur", entrenadorId, 10, 0, 1);
                            System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu Pokédex");
                            pokemonInicial.agregarPokemon(pokemonInicial);
                            esperar(3);
                            limpiarPantalla();
                            break;
                        case 2:
                            pokemonInicial = new PokemonFuego(4, "Charmander", entrenadorId, 10, 0, 1);
                            pokemonInicial.agregarPokemon(pokemonInicial);
                            System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu Pokédex");
                            esperar(3);
                            limpiarPantalla();
                            break;
                        case 3:
                            pokemonInicial = new PokemonAgua(7, "Squirtle", entrenadorId, 10, 0, 1);
                            pokemonInicial.agregarPokemon(pokemonInicial);
                            System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu Pokédex");
                            esperar(3);
                            limpiarPantalla();
                            break;
                        case 4:
                            pokemonInicial = new PokemonElectrico(25, "Pikachu", entrenadorId, 15, 0, 1);
                            pokemonInicial.agregarPokemon(pokemonInicial);
                            System.out.println(pokemonInicial.getNombre() + " ha sido añadido a tu Pokédex");
                            esperar(3);
                            limpiarPantalla();
                            break;
                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    limpiarPantalla();
                    break;
                case 2:
                    // Mostrar Pokédex
                    limpiarPantalla();
                    mostrarPokedex();
                    break;
                case 3:
                    limpiarPantalla();
                    Pokedex.inicializarPokedex();
                    Pokemon pokemonSalvaje = Pokedex.buscarPokemonSalvaje();
                    if (pokemonSalvaje == null) {
                        System.out.println("No se ha encontrado ningún Pokémon salvaje en esta zona.");
                        return; // O vuelve al menú principal
                    }

                    System.out.println("¡Te has encontrado con un " + pokemonSalvaje.getNombre() + " salvaje (Nivel " + pokemonSalvaje.getNivel() + ")!");
                    System.out.println("¿Qué quieres hacer?");
                    System.out.println("1. Pelear");
                    System.out.println("2. Intentar capturarlo");
                    System.out.println("3. Huir");
                    System.out.print("Seleccione una opción: ");
                    int accion = scanner.nextInt();
                    scanner.nextLine();

                    switch (accion) {
                        case 1:
                            iniciarPelea(pokemonSalvaje);
                            break;
                        case 2:
                            pokemonSalvaje.agregarPokemon(pokemonSalvaje);
                            limpiarPantalla();
                            System.out.println("Pokebola ve!");
                            System.out.println("1");
                            esperar(1);
                            System.out.println("2");
                            esperar(1);
                            System.out.println("3");
                            esperar(1);
                            System.out.println(pokemonSalvaje.getNombre() + " ha sido añadido a tu Pokédex");
                            esperar(3);
                            limpiarPantalla();
                            break;
                        case 3:
                            System.out.println("Has decidido huir.");
                            esperar(3);
                            limpiarPantalla();
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    break;
                case 4:
                    limpiarPantalla();
                    Rival rival = new Rival(0, "Gary", 15, "m");
                    System.out.println(rival.getNombre()+" te quiere retar a una batalla pokemon \n ¿Quieres aceptar el reto?\n 1. Si \n 2. No");
                    int reto = scanner.nextInt();
                    if(reto == 1){
                        iniciarReto();
                    }else{
                        limpiarPantalla();
                        break;
                    }
                    break;
                case 5: 
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

    public static void mostrarEquipoJugador() {
        for (int i = 0; i < Pokemon.getPokemons().size(); i++) {
            Pokemon pokemon = Pokemon.getPokemons().get(i);
            System.out.println((i + 1) + ". " + pokemon.getNombre() + " (Nivel: " + pokemon.getNivel() + ", Vida: " + pokemon.getVida() + ")");
        }
    }

    public static Pokemon elegirPokemonPelea(){
        limpiarPantalla();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nEscoge un Pokémon para pelear:");
        mostrarEquipoJugador();
        
        System.out.print("Seleccione el número de su Pokémon: ");
        int indice = scanner.nextInt();
        scanner.nextLine();
        
        if (indice < 1 || indice > Pokemon.getPokemons().size()) {
            System.out.println("Selección no válida.");
        }
        
        Pokemon pokemon = Pokemon.getPokemons().get(indice - 1);
        scanner.close();
        
        return pokemon;
    }
    
    public static void iniciarReto(){
        Pokemon pokemonJugador = elegirPokemonPelea();
        Pokemon pokemonRival = Rival.ajustarPokemonSegunNivel(pokemonJugador);
        System.out.println("Has elegido a " + pokemonJugador.getNombre() + " para pelear con " + pokemonRival.getNombre() + "!");

        // Bucle de batalla
        while (pokemonJugador.getVida() > 0 && pokemonRival.getVida() > 0) {
            // Turno del jugador
            pokemonJugador.atacar(pokemonRival);
            System.out.println(pokemonJugador.getNombre() + " ataca a " + pokemonRival.getNombre() + ". Vida de " + pokemonRival.getNombre() + ": " + pokemonRival.getVida());

            if (pokemonRival.getVida() <= 0) {
                System.out.println("¡" + pokemonRival.getNombre() + " ha sido derrotado!");
                esperar(6);
                //limpiarPantalla();
                break;
            }

            // Turno del Pokémon salvaje
            pokemonRival.atacar(pokemonJugador);
            System.out.println(pokemonRival.getNombre() + " ataca a " + pokemonJugador.getNombre() + ". Vida de " + pokemonJugador.getNombre() + ": " + pokemonJugador.getVida());

            if (pokemonJugador.getVida() <= 0) {
                System.out.println("¡Tu Pokémon " + pokemonJugador.getNombre() + " ha sido derrotado!");
                esperar(6);
                //limpiarPantalla();
                break;
            }
        }
    }

    public static void iniciarPelea(Pokemon pokemonSalvaje) {
        Pokemon pokemonJugador = elegirPokemonPelea();
        System.out.println("Has elegido a " + pokemonJugador.getNombre() + " para pelear con " + pokemonSalvaje.getNombre() + "!");

        // Bucle de batalla
        while (pokemonJugador.getVida() > 0 && pokemonSalvaje.getVida() > 0) {
            // Turno del jugador
            pokemonJugador.atacar(pokemonSalvaje);
            System.out.println(pokemonJugador.getNombre() + " ataca a " + pokemonSalvaje.getNombre() + ". Vida de " + pokemonSalvaje.getNombre() + ": " + pokemonSalvaje.getVida());

            if (pokemonSalvaje.getVida() <= 0) {
                System.out.println("¡" + pokemonSalvaje.getNombre() + " ha sido derrotado!");
                esperar(6);
                //limpiarPantalla();
                break;
            }

            // Turno del Pokémon salvaje
            pokemonSalvaje.atacar(pokemonJugador);
            System.out.println(pokemonSalvaje.getNombre() + " ataca a " + pokemonJugador.getNombre() + ". Vida de " + pokemonJugador.getNombre() + ": " + pokemonJugador.getVida());

            if (pokemonJugador.getVida() <= 0) {
                System.out.println("¡Tu Pokémon " + pokemonJugador.getNombre() + " ha sido derrotado!");
                esperar(6);
                //limpiarPantalla();
                break;
            }
        }
    }

}
