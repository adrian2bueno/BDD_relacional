package Model.DAO;

import Model.DatabaseConnection;
import Model.Players;
import Model.Teams;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeamsDAO implements DAOGenerica<Teams, Integer> {
    @Override
    public boolean insert(Teams team) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO teams (ciutat, nom, acronim, divisio, guanyades, perdudes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setString(1, team.getCiutat());
            pstmt.setString(2, team.getNom());
            pstmt.setString(3, team.getAcronim());
            pstmt.setString(4, team.getDivisio());
            pstmt.setInt(5, team.getGuanyades());
            pstmt.setInt(6,team.getPerdudes());

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
    public boolean update(Teams team) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE teams SET ciutat = ?, nom = ?, acronim = ?, divisio = ?, guanyades = ?, perdudes = ? WHERE equip_id = ?";
        try  {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setString(1, team.getCiutat());
            pstmt.setString(2, team.getNom());
            pstmt.setString(3, team.getAcronim());
            pstmt.setString(4, team.getDivisio());
            pstmt.setInt(5, team.getGuanyades());
            pstmt.setInt(6,team.getPerdudes());
            pstmt.setInt(7, team.getIdEquip());

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
        String sql = "DELETE FROM teams WHERE id = ?";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1, equip_id);
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
    public Teams findbyId(Integer integer) {
        return null;
    }
    public int count() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT COUNT(*) FROM teams";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rss = pstmt.executeQuery();
        rss.next();

        return rss.getInt(1);
    }

    //1 Llistar tots els jugadors d'un equip
    public List<Players> obtenirJugadors(String nomEquip) throws Exception {
        Connection connexio = DatabaseConnection.getConnection();
        String sql ="SELECT j.jugador_id,CONCAT(e.ciutat,' ',e.nom) AS nom_equip FROM players j INNER JOIN teams e ON j.equip_id = e.equip_id HAVING nom_equip = ?";
        PreparedStatement sentenciaJugadors = connexio.prepareStatement(sql);

        sentenciaJugadors.setString(1, nomEquip);
        ResultSet rsJugadors = sentenciaJugadors.executeQuery();

        List<Players> llistaPlayers;

        if (rsJugadors.next()) {
            llistaPlayers = new ArrayList<>();
            PlayerDAO playerDAO = new PlayerDAO();

            while (rsJugadors.next()) {
                llistaPlayers.add(playerDAO.cercar(rsJugadors.getInt("jugador_id")));
            }
        } else {
            throw new Exception("Equip no trobat");
        }

        return llistaPlayers;

    }

    public int cercarIdPerNom(String nomEquip) throws SQLException {
        Connection connexio = DatabaseConnection.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT equip_id,CONCAT(ciutat,' ',nom) AS nom_equip FROM teams HAVING nom_equip = ?"
        );

        sentencia.setString(1,nomEquip);
        ResultSet rsEquip = sentencia.executeQuery();

        if (rsEquip.next()) {
            int equipId = rsEquip.getInt("equip_id");
            return equipId;
        } else {
            return 0;
        }
    }
    @Override
    public List<Teams> findAll() {
        return null;
    }

    public List<Set<Map.Entry<String, Integer>>> obtenirResultatPartits(String equipNom) {
        return null;
    }
}
