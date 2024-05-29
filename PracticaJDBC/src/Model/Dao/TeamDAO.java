package Model.Dao;

import Controlador.DatabaseConnection;
import Model.Team;

import java.sql.*;
import java.util.List;

public class TeamDAO implements DAOGenerica<Team> {
    @Override
    public boolean insert(Team team) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO teams(nom) VALUES(?)";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, team.getNombre());

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
    public boolean update(Team team) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE teams SET nom=? WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, team.getNombre());
            pstmt.setInt(2, team.getId());

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
    public boolean delete(Team team) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM teams WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, team.getId());

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
    public Team llegir(Team team) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM teams WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, team.getId());

            ResultSet mResult = pstmt.executeQuery();
            if (mResult.next()) {
                Team t = new Team();
                t.setId(mResult.getInt(1));
                t.setNombre(mResult.getString(2));

                return t;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(pstmt);
        }
        return null;
    }
}
