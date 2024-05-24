package Exercicis;

import Model.DAO.PlayerDAO;
import Model.DAO.TeamsDAO;
import Model.Jugador;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exercicis {
    static TeamsDAO equipDAO = new TeamsDAO();
    static PlayerDAO playerDAO = new PlayerDAO();


    //1.- Llistar tots els jugadors d'un equip

    public static void exercici1(String equipNom) throws Exception {
        List<Jugador> jugadors = equipDAO.obtenirJugadors(equipNom);
        for (Jugador jugador : jugadors) {
            System.out.println(jugador.getNom() + " " + jugador.getCognom());
        }
    }
    //2.- Calcular la mitjana de punts, rebots, assistències, ... d'un jugador
    public static void exercici2(String jugadorNom) throws Exception {
        LinkedHashMap<String,Float> mitjanes = playerDAO.calcularMitjana(jugadorNom);
        for (Map.Entry<String, Float> entry : mitjanes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    //3.- Llistar tots els partits jugats per un equip amb el seu resultat.


    //4.- Inserir un nou jugador a un equip.



    //5.- Traspassar un judador a un altra equip



    //6.- Actualitzar les dades de jugadors o equips després d'un partit.


    //7.- Modificar les estadístiques d’un jugador


    //8.- Retirar (Eliminar) un jugador.


    //9.- Canviar nom franquícia d’un equip



}
