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
    public Teams findbyId(Integer integer) {
        return null;
    }

    @Override
    public boolean insert(Teams team) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO equips (id, ciutat, nom, acronim, divisio, guanyades, perdudes) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1, team.getIdEquip());
            pstmt.setString(2, team.getCiutat());
            pstmt.setString(3, team.getNom());
            pstmt.setString(4, team.getAcronim());
            pstmt.setString(5, team.getDivisio());
            pstmt.setInt(6, team.getGuanyades());
            pstmt.setInt(7, team.getPerdudes());

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
        String sql = "UPDATE equips SET id = ?, ciutat = ?, nom = ?, acronim = ?, divisio = ?, guanyades = ?, perdudes = ? WHERE equip_id = ?";
        try  {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1, team.getIdEquip());
            pstmt.setString(2, team.getCiutat());
            pstmt.setString(3, team.getNom());
            pstmt.setString(4, team.getAcronim());
            pstmt.setString(5, team.getDivisio());
            pstmt.setInt(6, team.getGuanyades()); // Modifiqué setBoolean a setInt
            pstmt.setInt(7, team.getPerdudes());  // Modifiqué setBoolean a setInt
            pstmt.setInt(8, team.getIdEquip()); // Ajusté el índice de los parámetros

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
        String sql = "DELETE FROM equips WHERE id = ?";
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

    public int count() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
                "SELECT COUNT(*) FROM equips"
        );

        ResultSet rss = pstmt.executeQuery();
        rss.next();

        return rss.getInt(1);
    }

    //1 Llistar tots els jugadors d'un equip
    public List<Players> obtenirJugadors(String nomEquip) throws Exception {
        Connection connexio = DatabaseConnection.getConnection();
        PreparedStatement sentenciaJugadors = connexio.prepareStatement(
                "SELECT j.jugador_id,CONCAT(e.ciutat,' ',e.nom) AS nom_equip FROM jugadors j INNER JOIN equips e ON j.equip_id = e.equip_id HAVING nom_equip = ?"
        );

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
                "SELECT equip_id,CONCAT(ciutat,' ',nom) AS nom_equip FROM equips HAVING nom_equip = ?"
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
