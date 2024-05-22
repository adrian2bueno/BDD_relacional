package Model.DAO;

import Model.Matches;
import Model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO implements DAOGenerica<Matches, Integer> {

    @Override
    public int count() throws SQLException {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = DatabaseConnection.getConnection();
            sentencia = connexio.prepareStatement(
                    "SELECT COUNT(*) FROM partits"
            );

            ResultSet rsNumPartits = sentencia.executeQuery();
            rsNumPartits.next();
            return rsNumPartits.getInt(1);
        } catch (SQLException e) {
            return -1;
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
                if (connexio != null) {
                    connexio.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public Matches findbyId(Integer integer) {
        return null;
    }

    @Override
    public boolean insert(Matches match) {
        String sql = "INSERT INTO partits (partit_id, equip_id, data_partit, matx, resultat) VALUES (?, ?, ?, ?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, match.getPartit_id());
            pstmt.setInt(2, match.getEquip_id());
            pstmt.setDate(4, new java.sql.Date(match.getData_partit().getTime()));
            pstmt.setString(5, match.getMatx());
            pstmt.setString(6, match.getResultat());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Matches match) {
        String sql = "UPDATE partits SET equip_id = ?, data_partit = ?, matx = ?, resultat = ?, WHERE partit_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(2, match.getEquip_id());
            pstmt.setDate(4, new java.sql.Date(match.getData_partit().getTime()));
            pstmt.setString(5, match.getMatx());
            pstmt.setString(6, match.getResultat());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer equip_id) {
        String sql = "DELETE FROM partits WHERE equip_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, equip_id);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Matches> findAll() {
        List<Matches> matches = new ArrayList<>();
        String sql = "SELECT * FROM partits";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Matches match = new Matches(
                        rs.getInt("partit_id"),
                        rs.getInt("equip_id"),
                        rs.getDate("data_partit"),
                        rs.getString("matx"),
                        rs.getString("resultat")

                );
                matches.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }
}
