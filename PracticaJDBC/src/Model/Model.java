package Model;

import Controlador.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {
    public static int obtenerIdEquip(String nombre) throws SQLException {
        int id_equipo = -1;

        Connection con = null;
        PreparedStatement smt = null;
        String sql = "SELECT id FROM teams WHERE nom=?";

        try {
            con = DatabaseConnection.getConnection();

            smt = con.prepareStatement(sql);
            smt.setString(1, nombre);
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                id_equipo = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SQLException("No sha trobat aquest equip");
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(smt);
        }
        return id_equipo;
    }

    public static int obtenerIdJugador(String nombre) throws SQLException {
        int id_jug = -1;

        Connection con = null;
        PreparedStatement smt = null;
        String sql = "SELECT id FROM players WHERE nom=?";

        try {
            con = DatabaseConnection.getConnection();
            smt = con.prepareStatement(sql);
            smt.setString(1, nombre);
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                id_jug = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new SQLException("No sha trobat aquest jugador");
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(smt);
        }
        return id_jug;
    }

    public static String obtenerNombreEquipo(int id) throws SQLException {
        String nom = "";
        Connection con = null;
        PreparedStatement smt = null;
        String sql = "SELECT nom FROM teams WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();
            smt = con.prepareStatement(sql);

            smt.setInt(1, id);

            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                nom = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("No sha trobat aquest equip");
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(smt);
        }
        return nom;
    }

    public static String obtenerNombreJugador(int id_jugador) throws SQLException {
        String nom = "";

        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = DatabaseConnection.getConnection();
            if (con != null) {
                smt = con.prepareStatement("SELECT nom FROM players WHERE id=?");
                smt.setInt(1, id_jugador);
                ResultSet resultado = smt.executeQuery();
                if (resultado.next()) {
                    nom = resultado.getString(1);
                }
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new SQLException("Ha ocurrido un error al buscar este jugador");
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(smt);
        }
        // retorna menos uno si no se encuentran registros
        return nom;
    }

    public static int obtenerIdEquipoNomComplet(String nombre) {
        Connection con = null;
        PreparedStatement smt = null;
        int id = -1;
        try {
            con = DatabaseConnection.getConnection();
            if (con != null) {
                smt = con.prepareStatement("SELECT id FROM teams WHERE nom_complet=?");
                smt.setString(1, nombre);
                ResultSet id_team = smt.executeQuery();
                if (id_team.next()) {
                    id = id_team.getInt(1);
                }
            } else {
                throw new SQLException("No s'ha pogut establir la connexió");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(smt);
        }
        return id;
    }


    /**
     * funcion que retorna los datos de la tabla de player-matches
     *
     * @param nombre indica el nombre del jugador cuyos datos seran buscados
     * @return retona la lista de los partidos que ha jugado ese jugador concreto
     */
    public static List<PlayerMatches> obtenerResultPartidos(String nombre) {
        Connection con = null;
        PreparedStatement smt = null;
        List<PlayerMatches> resultados = new ArrayList<>();
        int id_jugador;
        try {
            id_jugador = obtenerIdJugador(nombre);
            con = DatabaseConnection.getConnection();
            if (con != null) {
                smt = con.prepareStatement("SELECT id_match,punts,rebots,assistencies FROM players_matches WHERE id_jugador=?");
                smt.setInt(1, id_jugador);
                ResultSet resultado = smt.executeQuery();
                while (resultado.next()) {
                    PlayerMatches p = new PlayerMatches();
                    p.setId_match(resultado.getInt(1));
                    p.setPunts(resultado.getInt(2));
                    p.setRebots(resultado.getInt(3));
                    p.setAssist(resultado.getInt(4));
                    resultados.add(p);
                }

                return resultados;
            } else {
                throw new SQLException("No s'ha pogut establir la connexió");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(smt);
        }
        return resultados;
    }

    //Obté els partits en un format concret fent ús d'un procedure a la BD
    //aquest procedure fa un select concat de cada partit-jugador ex -> lakers-nets

    public static List<String> obtenerPartidos(int id_jugador) {
        List<String> partidos = new ArrayList<>();
        Connection con;
        PreparedStatement smt;
        try {
            con = DatabaseConnection.getConnection();
            if (con != null) {
                smt = con.prepareStatement("CALL PartidosJugadores(?)");
                smt.setInt(1, id_jugador);
                ResultSet partido = smt.executeQuery();
                while (partido.next()) {
                    //  en una columna visitante-local
                    String col1 = partido.getString(1);
                    partidos.add(col1);
                }
                return partidos;
            } else {
                throw new SQLException("No s'ha pogut establir la connexió");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return partidos;
    }


    //Funcio per cercar similituds amb la franquícia que ha inserit l'usuari dins de la base de dades
    public static List<String> buscadorFranquiciaBD(String pal_clave) {
        List<String> selecciones = new ArrayList<>();
        String sql = "SELECT nom_complet FROM teams WHERE nom_complet LIKE ?";
        Connection con;
        PreparedStatement smt;

        try {
            con = DatabaseConnection.getConnection();
            if (con != null) {
                smt = con.prepareStatement(sql);
                smt.setString(1, "%" + pal_clave + "%");
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    selecciones.add(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return selecciones;
    }
}
