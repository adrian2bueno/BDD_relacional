package Model.Dao;

import Controlador.DatabaseConnection;
import Model.Match;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO implements DAOGenerica<Match> {
    @Override
    public boolean insert(Match match) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO matches(id_visitante,punts_visitant,id_local,punts_local) VALUES(?,?,?,?);";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, match.getVisitante_id());
            pstmt.setInt(2, match.getPuntos_visitante());
            pstmt.setInt(3, match.getLocal_id());
            pstmt.setInt(4, match.getPuntos_local());

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
    public Match llegir(Match match) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "SELECT * FROM matches WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, match.getId());

            ResultSet mResult = pstmt.executeQuery();
            if (mResult.next()) {
                Match m = new Match();
                m.setId(mResult.getInt(1));
                m.setVisitante_id(mResult.getInt(2));
                m.setPuntos_visitante(mResult.getInt(3));
                m.setLocal_id(mResult.getInt(4));
                m.setPuntos_local(mResult.getInt(5));
                return m;
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
    public boolean update(Match match) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE matches SET id_visitante=?, punts_visitant=?, id_local=?, punts_local=? WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, match.getVisitante_id());
            pstmt.setInt(2, match.getPuntos_visitante());
            pstmt.setInt(3, match.getLocal_id());
            pstmt.setInt(4, match.getPuntos_local());
            pstmt.setInt(5, match.getId());

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
    public boolean delete(Match match) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM matches WHERE id=?";

        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, match.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(pstmt);
        }
    }

    public List<String> resultPartits(String nom) {
        List<String> partidos = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        //Aquet SELECT agafa els resultats
        //dels partits que involucren un equip específic de la base de dades.
        //Concatena els noms dels equips visitants i locals amb els seus respectius punts
        //en una sola cadena per a cada partit.
        String sql = "SELECT CONCAT(te1.nom, ' - ', te2.nom, ': ', ma.punts_visitant, '-', ma.punts_local) AS match_result " +
                "FROM matches ma " +
                "INNER JOIN teams te1 ON ma.id_visitante = te1.id " +
                "INNER JOIN teams te2 ON ma.id_local = te2.id " +
                "WHERE ma.id_visitante = (SELECT id FROM teams WHERE nom = ?) " +
                "OR ma.id_local = (SELECT id FROM teams WHERE nom = ?)";


        try {
            con = DatabaseConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, nom);
            pstmt.setString(2, nom);
            ResultSet partido = pstmt.executeQuery();
            while (partido.next()) {
                String col1 = partido.getString(1);
                partidos.add(col1);
            }
            return partidos;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            DatabaseConnection.close(con);
            DatabaseConnection.close(pstmt);
        }
        return null;
    }
}
