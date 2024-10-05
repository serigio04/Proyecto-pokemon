package main.java.Proyecto;
import java.util.Scanner;

public class Proyecto {
    
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
        Scanner scanner = new Scanner(System.in);
        String genero;
        int opcion = 0;
        String nombre;
        int edad;

        do{
            limpiarPantalla();

            System.out.println("    Pokemon     ");
            limpiarPantalla();
            esperar(3);

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
            limpiarPantalla();
            esperar(1);

            //Pokemon inicial
            System.out.println("Elige tu pokemon inicial");
        }
        while (true);

    }
}
