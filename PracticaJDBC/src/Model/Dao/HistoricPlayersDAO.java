package Model.Dao;

import Controlador.DatabaseConnection;
import Model.HistoricPlayers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoricPlayersDAO implements DAOGenerica<HistoricPlayers> {


    @Override
    public boolean insert(HistoricPlayers hp) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO historic_players(id,punts,rebots,assistencies,ultim_equip) VALUES(?,?,?,?,?)";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, hp.getId_jugador());
            pstmt.setFloat(2, hp.getPunts());
            pstmt.setFloat(3, hp.getRebots());
            pstmt.setFloat(4, hp.getAssist());
            pstmt.setString(5, hp.getUltim_equip());

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
    public HistoricPlayers llegir(HistoricPlayers historicPlayers) {
        return null;
    }

    @Override
    public boolean update(HistoricPlayers historicPlayers) {
        return false;
    }

    @Override
    public boolean delete(HistoricPlayers historicPlayers) {
        return false;
    }
}
