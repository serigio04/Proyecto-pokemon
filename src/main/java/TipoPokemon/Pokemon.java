package main.java.TipoPokemon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        this.vida = vida;
        this.experiencia = experiencia;
        this.nivel = nivel;
    }

    public Pokemon (){}

// Métodos get
    public int getNumPokedex() {
        return numPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public Entrenador getEntrenador(){
        return entrenador;
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
        this.vida += vida*1.4;
    }

    public void huir(){
        System.out.println("Tu y " + this.nombre + " han huido. \n La batalla ha terminado.");
    }

    public void ganarExperiencia() {
        this.experiencia += Rival.vida / 3;
        System.out.println(this.nombre + " ha ganado experiencia. Experiencia total: " + this.experiencia);
    }

    private double calcularExperienciaNecesaria() {
        return 1.3 * this.nivel;    }

    public void subirNivel(Connection conexion){
        if (this.experiencia >= calcularExperienciaNecesaria()) {
            this.nivel++;
            this.vida += vida / 2.6;
            xpBase = 15;
    
            System.out.println(this.nombre + " ha subido al nivel " + this.nivel);
    
            evolucionar(conexion);
        } else {
            System.out.println(this.nombre + " aún no tiene suficiente experiencia para subir de nivel.");
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
                            actualizarPokemonEnBD(conexion);
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

    public void actualizarPokemonEnBD(Connection conexion) {
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
                System.out.println("El Pokémon ha sido actualizado en la base de datos.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No hay conexión con la base de datos.");
        }
    }
}