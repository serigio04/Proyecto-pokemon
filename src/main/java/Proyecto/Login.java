import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {

    // Mapa para almacenar usuarios y contraseñas
    private static Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("---- Sistema de Login ----");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }

        scanner.close();
    }

    // Método para registrar un nuevo usuario
    private static void registerUser(Scanner scanner) {
        System.out.print("Ingrese un nombre de usuario: ");
        String username = scanner.nextLine();

        // Verificar si el usuario ya existe
        if (users.containsKey(username)) {
            System.out.println("El nombre de usuario ya existe. Inténtalo con otro.");
            return;
        }

        System.out.print("Ingrese una contraseña: ");
        String password = scanner.nextLine();

        // Almacenar las credenciales en el mapa
        users.put(username, password);
        System.out.println("¡Usuario registrado exitosamente!");
    }

    // Método para iniciar sesión con un usuario existente
    private static void loginUser(Scanner scanner) {
        System.out.print("Ingrese su nombre de usuario: ");
        String username = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        // Verificar las credenciales
        if (authenticate(username, password)) {
            System.out.println("¡Login exitoso! Bienvenido, " + username);
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }

    // Método para autenticar al usuario
    private static boolean authenticate(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}
