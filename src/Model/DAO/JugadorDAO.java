package Model.DAO;

import Model.DatabaseConnection;
import Model.Jugador;

import java.sql.*;
import java.util.List;

//TODO: Falta doRsal en TODO MENOS FINDBYID
public class JugadorDAO implements DAOGenerica<Jugador, Integer> {
    @Override
    public Jugador findbyId(Integer jugadorId) {
        String sql = "SELECT * FROM jugadors WHERE jugador_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jugadorId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Jugador(
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
    public boolean insert(Jugador jugador) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO jugadors (jugador_id, nom, cognom, data_naixement, alcada, pes, posicio, equip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1, jugador.getJugadorId());
            pstmt.setString(2, jugador.getNom());
            pstmt.setString(3, jugador.getCognom());
            pstmt.setDate(4, new Date(jugador.getDataNaixement().getTime()));
            pstmt.setString(5, jugador.getAlcada());
            pstmt.setString(6, jugador.getPes());
            pstmt.setString(7, jugador.getPosicio());
            pstmt.setInt(8, jugador.getEquipId());

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
    public boolean update(Jugador players) {
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
    public Jugador cercar(int id) throws SQLException {
        Connection connexio = DatabaseConnection.getConnection();
        PreparedStatement sentencia = connexio.prepareStatement(
                "SELECT * FROM jugadors WHERE jugador_id = ?"
        );

        sentencia.setInt(1,id);
        ResultSet rsJugador = sentencia.executeQuery();

        if (rsJugador.next()) {
            Jugador jugador = new Jugador(
                    rsJugador.getInt("jugadorId"),
                    rsJugador.getString("nom"),
                    rsJugador.getString("cognom"),
                    rsJugador.getDate("data_naixement"),
                    rsJugador.getString("alcada"),
                    rsJugador.getString("pes"),
                    rsJugador.getString("dorsal"),
                    rsJugador.getString("posicio"),
                    rsJugador.getInt("equip_id"));

            jugador.setJugadorId(rsJugador.getInt("jugador_id"));
            return jugador;
        } else {
            return null;
        }
    }


    @Override
    public List<Jugador> findAll() {
        return null;
    }

}
