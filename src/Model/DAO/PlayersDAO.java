package Model.DAO;

import Model.DatabaseConnection;
import Model.Model;
import  Model.Players;

import java.sql.*;
import java.util.List;

public class PlayersDAO implements DAOGenerica<Players, Integer> {
    @Override
    public Players findbyId(Integer jugadorId) {
        return null;
    }

    @Override
    public boolean insert(Players players) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1, players.getJugadorId());
            pstmt.setString(2, players.getNom());
            pstmt.setString(3, players.getCognom());
            pstmt.setDate(4, new Date(players.getDataNaixement().getTime()));
            pstmt.setString(5, players.getAlcada());
            pstmt.setString(6, players.getPes());
            pstmt.setString(7, players.getPosicio());
            pstmt.setInt(8, players.getEquipId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                if (pstmt != null){
                    pstmt.close();
                }
                if (conexio != null){
                    conexio.close();
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }


    @Override
    public boolean update(Players players) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE jugadors SET nom = ?, cognom = ?, data_naixement = ?, alcada = ?, pes = ?, posicio = ?, equip_id = ? WHERE jugador_id = ?";
        try  {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setString(1, players.getNom());
            pstmt.setString(2, players.getCognom());
            pstmt.setDate(3, new Date(players.getDataNaixement().getTime()));
            pstmt.setString(4, players.getAlcada());
            pstmt.setString(5, players.getPes());
            pstmt.setString(6, players.getPosicio());
            pstmt.setInt(7, players.getEquipId());
            pstmt.setInt(8, players.getJugadorId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }finally {
            try {
                if (pstmt != null){
                    pstmt.close();
                }
                if (conexio != null){
                    conexio.close();
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public boolean delete(Integer jugador_id) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM jugadors WHERE jugador_id = ?";

        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1,jugador_id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexio != null) {
                    conexio.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Players> findAll() {
        return null;
    }

}
