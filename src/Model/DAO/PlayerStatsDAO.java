package Model.DAO;

import Model.DatabaseConnection;
import Model.PlayerStats;

import java.sql.*;
import java.util.List;

public class PlayerStatsDAO implements DAOGenerica<PlayerStats, Integer> {
    @Override
    public boolean insert(PlayerStats playerStat) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO player_stats (jugador_id,equip_id,partit_id,minuts_jugats,punts,tirs_anotats,tirs_tirats,tirs_triples_antotats,tirs_triples_tirats,tirs_lliures_anotats,tirs_lliures_tirats,rebots_ofensius,rebots_defensius,assistencies,robades,bloqueigs) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1,playerStat.getJugadorId());
            pstmt.setInt(2,playerStat.getEquipId());
            pstmt.setInt(3,playerStat.getPartitId());
            pstmt.setFloat(4,playerStat.getMinutsJugats());
            pstmt.setInt(5,playerStat.getPunts());
            pstmt.setInt(6,playerStat.getTirsAnotats());
            pstmt.setInt(7,playerStat.getTirsTirats());
            pstmt.setInt(8,playerStat.getTirsTriplesAnotats());
            pstmt.setInt(9,playerStat.getTirsTriplesTirats());
            pstmt.setInt(10,playerStat.getTirsLliuresAnotats());
            pstmt.setInt(11,playerStat.getTirsLliuresTirats());
            pstmt.setInt(12,playerStat.getRebotsOfensius());
            pstmt.setInt(13,playerStat.getRebotsDefensius());
            pstmt.setInt(14,playerStat.getAssistencies());
            pstmt.setInt(15,playerStat.getRobades());
            pstmt.setInt(16,playerStat.getBloqueigs());

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
    public boolean update(PlayerStats playerStat) {
        Connection conexio = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE player_stats SET equip_id=?,partit_id=?,minuts_jugats=?,punts=?,tirs_anotats=?,tirs_tirats=?,tirs_triples_antotats=?,tirs_triples_tirats=?,tirs_lliures_anotats=?,tirs_lliures_tirats=?,rebots_ofensius=?,rebots_defensius=?,assistencies=?,robades=?,bloqueigs=? WHERE jugador_id=?";
        try  {
            conexio = DatabaseConnection.getConnection();
            pstmt = conexio.prepareStatement(sql);

            pstmt.setInt(1,playerStat.getJugadorId());
            pstmt.setInt(2,playerStat.getEquipId());
            pstmt.setInt(3,playerStat.getPartitId());
            pstmt.setFloat(4,playerStat.getMinutsJugats());
            pstmt.setInt(5,playerStat.getPunts());
            pstmt.setInt(6,playerStat.getTirsAnotats());
            pstmt.setInt(7,playerStat.getTirsTirats());
            pstmt.setInt(8,playerStat.getTirsTriplesAnotats());
            pstmt.setInt(9,playerStat.getTirsTriplesTirats());
            pstmt.setInt(10,playerStat.getTirsLliuresAnotats());
            pstmt.setInt(11,playerStat.getTirsLliuresTirats());
            pstmt.setInt(12,playerStat.getRebotsOfensius());
            pstmt.setInt(13,playerStat.getRebotsDefensius());
            pstmt.setInt(14,playerStat.getAssistencies());
            pstmt.setInt(15,playerStat.getRobades());
            pstmt.setInt(16,playerStat.getBloqueigs());

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
        Connection connexio = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM estadistiques_jugadors WHERE jugador_id = ?";

        try {
            connexio = DatabaseConnection.getConnection();
            pstmt = connexio.prepareStatement(sql);

            pstmt.setInt(1,id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
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
    public PlayerStats findbyId(Integer o) {
        return null;
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
                    "SELECT COUNT(*) FROM player_stats"
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
        String sql = "DELETE FROM player_stats WHERE jugador_id = ? AND partit_id = ?";
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
