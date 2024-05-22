package Model.DAO;

import Model.DatabaseConnection;
import Model.EstadistiquesJugador;
import Model.Jugador;

import java.sql.*;
import java.util.List;

public class EstadistiquesJugadorDAO implements DAOGenerica<EstadistiquesJugador, Integer> {
    @Override
    public EstadistiquesJugador findbyId(Integer o) {
        return null;
    }

    @Override
    public boolean insert(EstadistiquesJugador estJugador) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO estadistiques_jugadors (jugador_id,equip_id,partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_antotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,rebots_defensius,assistencies,robades,bloqueigs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1,estJugador.getJugadorId());
            pstmt.setInt(2,estJugador.getEquipId());
            pstmt.setInt(3,estJugador.getPartitId());
            pstmt.setFloat(4,estJugador.getMinutsJugats());
            pstmt.setInt(5,estJugador.getPunts());
            pstmt.setInt(6,estJugador.getTirsAnotats());
            pstmt.setInt(7,estJugador.getTirsTirats());
            pstmt.setInt(8,estJugador.getTirsTriplesAnotats());
            pstmt.setInt(9,estJugador.getTirsTriplesTirats());
            pstmt.setInt(10,estJugador.getTirsLliuresAnotats());
            pstmt.setInt(11,estJugador.getTirsLliuresTirats());
            pstmt.setInt(12,estJugador.getRebotsOfensius());
            pstmt.setInt(13,estJugador.getRebotsDefensius());
            pstmt.setInt(14,estJugador.getAssistencies());
            pstmt.setInt(15,estJugador.getRobades());
            pstmt.setInt(16,estJugador.getBloqueigs());

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
    public boolean update(EstadistiquesJugador estJugador) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE estadistiques_jugadors SET equip_id=?,partit_id=?,minuts_jugats=?,punts=?,tirs_anotats=?,tirs_tirats=?,tirs_triples_antotats=?,tirs_triples_tirats=?,tirs_lliures_anotats=?,tirs_lliures_tirats=?,rebots_ofensius=?,rebots_defensius=?,assistencies=?,robades=?,bloqueigs=? WHERE jugador_id=?";
        try  {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1,estJugador.getJugadorId());
            pstmt.setInt(2,estJugador.getEquipId());
            pstmt.setInt(3,estJugador.getPartitId());
            pstmt.setFloat(4,estJugador.getMinutsJugats());
            pstmt.setInt(5,estJugador.getPunts());
            pstmt.setInt(6,estJugador.getTirsAnotats());
            pstmt.setInt(7,estJugador.getTirsTirats());
            pstmt.setInt(8,estJugador.getTirsTriplesAnotats());
            pstmt.setInt(9,estJugador.getTirsTriplesTirats());
            pstmt.setInt(10,estJugador.getTirsLliuresAnotats());
            pstmt.setInt(11,estJugador.getTirsLliuresTirats());
            pstmt.setInt(12,estJugador.getRebotsOfensius());
            pstmt.setInt(13,estJugador.getRebotsDefensius());
            pstmt.setInt(14,estJugador.getAssistencies());
            pstmt.setInt(15,estJugador.getRobades());
            pstmt.setInt(16,estJugador.getBloqueigs());

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

    //TODO
    @Override
    public boolean delete(Integer id) {
        throw new UnsupportedOperationException("Método no soportado para EstadistiquesJugador");

    }


    @Override
    public List findAll() {
        return null;
    }

    //TODO adnfhkadbfiahd
    @Override
    public int count() throws SQLException {
        Connection connexio = null;
        PreparedStatement sentencia = null;

        try {
            connexio = DatabaseConnection.getConnection();
            sentencia = connexio.prepareStatement(
                    "SELECT COUNT(*) FROM estadistiques_jugadors"
            );

            ResultSet rsEstadisticaJugador = sentencia.executeQuery();
            rsEstadisticaJugador.next();
            return rsEstadisticaJugador.getInt(1);
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
    //Funcio per trobar
    public boolean borrarEstadistiquesJugador(int jugador_id, int partit_id) {
        String sql = "DELETE FROM estadistiques_jugadors WHERE jugador_id = ? AND partit_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, jugador_id);
            pstmt.setInt(2, partit_id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
