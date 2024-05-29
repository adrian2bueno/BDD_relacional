package Model.Dao;

import Controlador.DatabaseConnection;
import Model.PlayerMatches;

import java.sql.*;

public class PlayerMatchesDAO implements DAOGenerica<PlayerMatches> {

    @Override
    public boolean insert(PlayerMatches playerMatches) {
        return false;
    }

    @Override
    public PlayerMatches llegir(PlayerMatches pm) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM players_matches WHERE id_match=? AND id_jugador=?";
        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, pm.getId_match());
            pstmt.setInt(2, pm.getId_jug());

            ResultSet mResult = pstmt.executeQuery();
            if (mResult.next()) {
                PlayerMatches m = new PlayerMatches();
                m.setId_match(mResult.getInt(1));
                m.setId_jug(mResult.getInt(2));
                m.setPunts(mResult.getInt(3));
                m.setRebots(mResult.getInt(5));
                m.setAssist(mResult.getInt(4));
                return m;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(pstmt);
        }
        return null;
    }

    @Override
    public boolean update(PlayerMatches pm) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE players_matches SET punts=?,rebots=?,assist=? WHERE id_match=? AND id_jugador=?";

        try {
            con = DatabaseConnection.getConnection();
                pstmt = con.prepareStatement(sql);

                pstmt.setInt(1, pm.getPunts());
                pstmt.setInt(2, pm.getRebots());
                pstmt.setInt(3, pm.getAssist());
                pstmt.setInt(4, pm.getId_match());
                pstmt.setInt(5, pm.getId_jug());

                return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(pstmt);
        }
    }

    @Override
    public boolean delete(PlayerMatches playerMatches) {
        return false;
    }
}
