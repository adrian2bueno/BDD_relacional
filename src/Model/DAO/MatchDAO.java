package Model.DAO;

import Model.Matches;
import Model.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO implements DAOGenerica<Matches, Integer> {
    @Override
    public boolean insert(Matches matches) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO matches (partit_id, equip_id, data_partit, matx, resultat) VALUES (?, ?, ?, ?,?)";
        try{
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1, matches.getPartit_id());
            pstmt.setInt(2, matches.getEquip_id());
            pstmt.setDate(4, new java.sql.Date(matches.getData_partit().getTime()));
            pstmt.setString(5, matches.getMatx());
            pstmt.setString(6, matches.getResultat());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Matches matches) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE matches SET equip_id = ?, data_partit = ?, matx = ?, resultat = ?, WHERE partit_id = ?";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(2, matches.getEquip_id());
            pstmt.setDate(4, new java.sql.Date(matches.getData_partit().getTime()));
            pstmt.setString(5, matches.getMatx());
            pstmt.setString(6, matches.getResultat());

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
    public boolean delete(Integer equip_id) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM matches WHERE equip_id = ?";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1, equip_id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Matches> findAll() {
        List<Matches> matches = new ArrayList<>();
        String sql = "SELECT * FROM matches";
        try (Connection conexio = DatabaseConnection.getConnection();
             Statement stmt = conexio.createStatement();
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
    @Override
    public int count() throws SQLException {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = DatabaseConnection.getConnection();
            sentencia = connexio.prepareStatement(
                    "SELECT COUNT(*) FROM matches"
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
}
