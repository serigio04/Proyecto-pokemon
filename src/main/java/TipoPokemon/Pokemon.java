package main.java.TipoPokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import main.java.Proyecto.Entrenador;

public abstract class Pokemon {
    protected int numPokedex;
    protected String nombre;
    protected double vida;
    protected double vidaMaxima;  // Nueva variable para diferenciar la vida máxima de la actual
    protected double experiencia;
    protected int nivel;
    Entrenador entrenador;
    protected int entrenadorId;
    protected int danioBase;
    protected int xpBase;

    public Pokemon (int numPokedex, int entrenadorId, String nombre, double vida, double experiencia, int nivel){
        this.numPokedex = numPokedex;
        this.entrenadorId = entrenadorId;
        this.nombre = nombre;
        this.vida = vida;
        this.vidaMaxima = vida;
        this.experiencia = experiencia;
        this.nivel = nivel;
    }

    public Pokemon (){}

// Arraylist
    private static ArrayList<Pokemon> pokemons = new ArrayList<>();
// Métodos get
    public int getNumPokedex() {
        return numPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEntrenadorId(){
        return entrenadorId;
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

    public static ArrayList<Pokemon> getPokemons() {
        return pokemons;
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

    public void setEntrenadorId(int id){
        this.entrenadorId = id;
    }
// metodos de la clases
    public void atacar(Pokemon rival){
        double critico = Math.random() < 0.1 ? 2 : 1;
        System.out.println(this.nombre + " ataca!");
        double danio = ((this.vida * 0.1) + danioBase) * (nivel * 0.5) * critico;
        danio = Math.round(danio * 100.0) / 100.0;

        if (critico == 2) {
            System.out.println(this.nombre + " ha hecho un golpe crítico");
        }
        danio = Math.max(danio, 0);
        rival.recibirDanio(danio);
    }

    public void atacar(Pokemon rival, double danio){
        rival.recibirDanio(danio);
    }

    public void recibirDanio(double danio) {
        this.vida -= danio;
        System.out.println(this.nombre + " ha recibido " + danio + " puntos de ataque");
        System.out.println("La vida actual de"+this.getNombre()+" es de "+this.getVida());
        if (this.vida <= 0) {
            this.vida = 0;
            System.out.println(this.nombre + " ha sido derrotado.");}
    }

    public void curarse(){
        this.vida += vida*1.4;
    }

    // public void ganarExperiencia() {
    //     this.experiencia += this.vida * this.nivel / 4;
    //     System.out.println(this.nombre + " ha ganado experiencia. Experiencia total: " + this.experiencia);
    //     while (this.experiencia >= calcularExperienciaNecesaria()) {
    //         subirNivel(null);
    //     }
    //     if (debeEvolucionar()) {
    //         evolucionar(); 
    //     }
    // }

    private double calcularExperienciaNecesaria() {
        return 1.3 * this.nivel;    }

    // public void subirNivel(Pokemon pokemon){
    //     this.experiencia -= experienciaNecesaria;
    //         this.nivel++;
    //         this.vidaMaxima += vidaMaxima / 2.6;
    //         this.vida = this.vidaMaxima;
    //         xpBase += 15;
    
    //         System.out.println(this.nombre + " ha subido al nivel " + this.nivel);
    
    //         evolucionar(pokemon);
    //     } else {
    //         System.out.println(this.nombre + " aún no tiene suficiente experiencia para subir de nivel.");
    //     }
    // }

    public void evolucionar(Pokemon pokemon){
        if (true) {
            
        }
    }

    public void evolucionar(Connection conexion) {
        if (this.nivel == 16 || this.nivel == 35) {
            try {
                // Consulta a la base de datos para obtener la evolución
                String sql = "SELECT idEvolucion FROM pokedex WHERE id = ?";
                PreparedStatement pstmt = conexion.prepareStatement(sql);
                pstmt.setInt(1, this.numPokedex);  // Usamos el id del Pokémon actual

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    int idEvolucion = rs.getInt("idEvolucion");

                    if (idEvolucion != 0) {
                        // Hay una evolución disponible
                        System.out.println(this.nombre + " ha evolucionado!");

                        // Obtener el nuevo nombre del Pokémon evolucionado
                        String evolucionSql = "SELECT nombre FROM pokedex WHERE id = ?";
                        PreparedStatement pstmtEvolucion = conexion.prepareStatement(evolucionSql);
                        pstmtEvolucion.setInt(1, idEvolucion);

                        ResultSet rsEvolucion = pstmtEvolucion.executeQuery();
                        if (rsEvolucion.next()) {
                            this.nombre = rsEvolucion.getString("nombre");

                            // Actualizar los datos del Pokémon en la base de datos
                            actualizarPokemon(conexion);
                            System.out.println("¡Felicidades! " + this.nombre + " es la nueva forma evolucionada.");
                        }

                        rsEvolucion.close();
                        pstmtEvolucion.close();
                    } else {
                        System.out.println(this.nombre + " no puede evolucionar.");
                    }
                }
                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizarPokemon(Connection conexion) {
        if (conexion != null) {
            String sql = "UPDATE pokemon SET nombre = ?, nivel = ?, vida = ?, experiencia = ? WHERE numeroPokedex = ? AND entrenador = ?";
    
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, this.nombre);  // Nuevo nombre del Pokémon si ha evolucionado
                pstmt.setInt(2, this.nivel);      // Nivel actualizado
                pstmt.setDouble(3, this.vida);    // Vida actualizada
                pstmt.setDouble(4, this.experiencia); // Experiencia actualizada
                pstmt.setInt(5, this.numPokedex); // Número en la Pokédex
                pstmt.setInt(6, this.entrenador.getId()); // ID del entrenador
    
                pstmt.executeUpdate();
                for (int i = 0; i < pokemons.size(); i++) {
                    Pokemon p = pokemons.get(i);
                    if (p.numPokedex == this.numPokedex && p.entrenador.getId() == this.entrenador.getId()) {
                        pokemons.set(i, this); // Actualizar Pokémon en el ArrayList
                        break;
                    }
                }
                System.out.println("El Pokémon ha sido actualizado en la pokedex.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay conexión con la base de datos.");
        }
    }

//metodo para agregar a la pokedex ( tabla pokemon en la database )
    public void agregarPokemon(Connection conexion) {
        if (conexion != null) {
            String sql = "INSERT INTO pokemon (nombre, tipo, entrenador_id, nivel, vida, experiencia) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, this.nombre); // Nombre del Pokémon
                pstmt.setString(2, this.getClass().getSimpleName()); // Tipo del Pokémon
                pstmt.setInt(3, this.entrenador.getId()); // ID del entrenador
                pstmt.setInt(4, this.nivel); // Nivel inicial
                pstmt.setDouble(5, this.vida); // Vida inicial
                pstmt.setDouble(6, this.experiencia); // Experiencia inicial

                // Ejecutar la inserción
                pstmt.executeUpdate();
                pokemons.add(this);
                System.out.println(this.nombre + " ha sido guardado en la pokedex.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay conexión con la base de datos.");
        }
    }

    public void agregarPokemon(Pokemon pokemon){
        pokemons.add(this);
    }

}