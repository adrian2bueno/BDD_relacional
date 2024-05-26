package Model.DAO;

import Model.DatabaseConnection;
import Model.Players;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.List;

//TODO: Falta doRsal en TODO MENOS FINDBYID
public class PlayerDAO implements DAOGenerica<Players, Integer> {
    @Override
    public Players findbyId(Integer jugadorId) {
        String sql = "SELECT * FROM jugadors WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jugadorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Players(
                        rs.getInt("jugador_id"),
                        rs.getString("nom"),
                        rs.getString("cognom"),
                        rs.getDate("data_naixement"),
                        rs.getString("alcada"),
                        rs.getString("pes"),
                        rs.getString("dorsal"), // Añadido campo dorsal
                        rs.getString("posicio"),
                        rs.getInt("equip_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public int count() throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
                "SELECT COUNT(*) FROM jugadors"
        );

        ResultSet rss = pstmt.executeQuery();
        rss.next();

        return rss.getInt(1);
    }
    public Players cercar(int id) throws SQLException {
        Connection connexio = DatabaseConnection.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM jugadors WHERE jugador_id = ?"
        );

        sentencia.setInt(1,id);
        ResultSet rsJugador = sentencia.executeQuery();

        if (rsJugador.next()) {
            Players players = new Players(
                    rsJugador.getInt("jugadorId"),
                    rsJugador.getString("nom"),
                    rsJugador.getString("cognom"),
                    rsJugador.getDate("data_naixement"),
                    rsJugador.getString("alcada"),
                    rsJugador.getString("pes"),
                    rsJugador.getString("dorsal"),
                    rsJugador.getString("posicio"),
                    rsJugador.getInt("equip_id"));

            players.setJugadorId(rsJugador.getInt("jugador_id"));
            return players;
        } else {
            return null;
        }
    }
    public int cercarIdPerNom(String nomComplet) throws SQLException {
        Connection connexio = DatabaseConnection.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT jugador_id,CONCAT(nom,' ',cognom) AS nom_complet FROM jugadors HAVING nom_complet = ?"
        );

        sentencia.setString(1,nomComplet);
        ResultSet rsJugador = sentencia.executeQuery();

        if (rsJugador.next()) {
            return rsJugador.getInt("jugador_id");
        } else {
            return 0;
        }
    }

    @Override
    public List<Players> findAll() {
        return null;
    }

    public LinkedHashMap<String, Float> calcularMitjana(String jugadorNom) {
        return null;
    }
}
