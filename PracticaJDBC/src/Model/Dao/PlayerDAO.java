package Model.Dao;

import Controlador.DatabaseConnection;
import Controlador.Controlador;
import Model.Player;
import Model.Players_stats;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO implements DAOGenerica<Player> {
    @Override
    public boolean insert(Player player) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO players(nom,alcada,pes,equipo_actual) VALUES(?,?,?,?)";
        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, player.getNom());
            pstmt.setInt(2, player.getAlcada());
            pstmt.setInt(3, player.getPes());
            pstmt.setInt(4, player.getEquip_actual());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Player llegir(Player player) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM players WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, player.getId());

            ResultSet mResult = pstmt.executeQuery();
            if (mResult.next()) {
                Player p = new Player();
                p.setId(mResult.getInt(1));
                p.setNom(mResult.getString(2));
                p.setAlcada(mResult.getInt(3));
                p.setPes(mResult.getInt(4));
                p.setEquip_actual(mResult.getInt(5));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean update(Player player) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE players SET nom=?, alcada=?, pes=?, equipo_actual=? Where id=?";
        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, player.getNom());
            pstmt.setInt(2, player.getAlcada());
            pstmt.setInt(3, player.getPes());
            pstmt.setInt(4, player.getEquip_actual());
            pstmt.setInt(5, player.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean delete(Player player) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM players WHERE id=?";
        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, player.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //Mètode per guardar en una array els stats d'un jugador concret
    public List<Players_stats> medianasPlayers(String nom) {
        List<Players_stats> jugadores = new ArrayList<>();
        Connection con = null;
        PreparedStatement smt = null;
        String sql = "SELECT id FROM players WHERE nom=?";
        String sql1 = "SELECT * FROM player_stats WHERE id_jugador=?";

        try {
            con = DatabaseConnection.getConnection();
            smt = con.prepareStatement(sql);

            smt.setString(1, nom);
            ResultSet id_jugador = smt.executeQuery();
            if (id_jugador.next()) {

                smt = con.prepareStatement(sql1);
                smt.setInt(1, id_jugador.getInt(1));
                ResultSet jugadors_media = smt.executeQuery();

                while (jugadors_media.next()) {
                    Players_stats p = new Players_stats();
                    p.setId_jugador(jugadors_media.getInt(1));
                    p.setAvg_puntos(jugadors_media.getFloat(2));
                    p.setAvg_rebotes(jugadors_media.getFloat(3));
                    p.setAvg_asistencias(jugadors_media.getFloat(4));
                    jugadores.add(p);
                }
                return jugadores;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(smt);
        }
        return null;
    }


    public List<Player> llistarJugadors(String nom) {
        List<Player> jugadores = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DatabaseConnection.getConnection();
            if (con != null) {
                pstmt = con.prepareStatement("SELECT id FROM teams WHERE nom=?");
                pstmt.setString(1, nom);
                ResultSet id_equipo = pstmt.executeQuery();
                if (id_equipo.next()) {
                    pstmt = con.prepareStatement("SELECT * FROM players WHERE equipo_actual=?");
                    pstmt.setInt(1, id_equipo.getInt("id"));
                    ResultSet jugadors_team = pstmt.executeQuery();
                    while (jugadors_team.next()) {
                        Player p = new Player();
                        p.setId(jugadors_team.getInt(1));
                        p.setNom(jugadors_team.getString(2));
                        p.setAlcada(jugadors_team.getInt(3));
                        p.setPes(jugadors_team.getInt(4));
                        p.setEquip_actual(jugadors_team.getInt(5));
                        jugadores.add(p);
                    }
                } else {
                    throw new SQLException("Ha ocurrido un error al buscar los jugadores de ese equipo");
                }
                return jugadores;
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return jugadores;
    }


    public List<Player> listarTodos(String nom) {
        List<Player> jugadores = new ArrayList<>();
        Connection con = null;
        PreparedStatement smt = null;
        try {
            con = DatabaseConnection.getConnection();
            if (con != null){
                smt = con.prepareStatement("SELECT id FROM teams WHERE nom=?");
                smt.setString(1,nom);
                ResultSet id_equipo = smt.executeQuery();
                if (id_equipo.next()){
                    smt = con.prepareStatement("SELECT * FROM players WHERE equipo_actual=?");
                    smt.setInt(1,id_equipo.getInt("id"));
                    ResultSet jugadors_team = smt.executeQuery();
                    while (jugadors_team.next()){
                        Player p = new Player();
                        p.setId(jugadors_team.getInt(1));
                        p.setNom(jugadors_team.getString(2));
                        p.setAlcada(jugadors_team.getInt(3));
                        p.setPes(jugadors_team.getInt(4));
                        p.setEquip_actual(jugadors_team.getInt(5));
                        jugadores.add(p);
                    }
                } else {
                    throw new SQLException ("Ha ocurrido un error");
                }
                return jugadores;
            } else {
                throw new SQLException("No se ha podido establecer la conexion");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(smt);
        }
        return null;
    }


}
