package Model.Dao;

import Controlador.DatabaseConnection;
import Model.Players_stats;

import java.sql.*;

public class PlayerStatsDAO implements DAOGenerica<Players_stats> {

    @Override
    public boolean insert(Players_stats playersStats) {
        return false;
    }

    @Override
    public Players_stats llegir(Players_stats ps) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM player_stats WHERE id_jugador=?";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, ps.getId_jugador());

            ResultSet mResult = pstmt.executeQuery();
            if (mResult.next()) {
                Players_stats p = new Players_stats();
                p.setId_jugador(mResult.getInt(1));
                p.setAvg_puntos(mResult.getInt(2));
                p.setAvg_rebotes(mResult.getInt(3));
                p.setAvg_asistencias(mResult.getInt(4));
                return p;
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
    public boolean update(Players_stats playersStats) {
        return false;
    }

    @Override
    public boolean delete(Players_stats ps) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM player_stats WHERE id_jugador=?";

        try {
            con = DatabaseConnection.getConnection();

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, ps.getId_jugador());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(pstmt);
        }
    }
}
