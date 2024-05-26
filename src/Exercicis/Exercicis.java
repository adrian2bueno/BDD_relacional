package Exercicis;

import Model.DAO.PlayerDAO;
import Model.DAO.TeamsDAO;
import Model.Players;

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
        List<Players> players = equipDAO.obtenirJugadors(equipNom);
        for (Players player : players) {
            System.out.println(player.getNom() + " " + player.getCognom());
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
    public static void exercici3(String equipNom) throws Exception {
        List<Set<Map.Entry<String,Integer>>> llista = equipDAO.obtenirResultatPartits(equipNom);
        for (Set<Map.Entry<String,Integer>> set : llista) {
            for (Map.Entry<String,Integer> entry : set) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }
    }

    //4.- Inserir un nou jugador a un equip.
    public static boolean exercici4(String jugadorNom, String equipNom) throws Exception {
        if (playerDAO.cercarIdPerNom(jugadorNom) == 0) {
            if (equipDAO.cercarIdPerNom(equipNom) != 0) {
                //Crear el jugador i insertar-lo

                String[] nomCognom = jugadorNom.split(" ");
                String nom;
                String cognom;

                if (nomCognom.length > 2) {
                    nom = nomCognom[0] + " " + nomCognom[1];
                    cognom = nomCognom[2];
                } else {
                    nom = nomCognom[0];
                    cognom = nomCognom[1];
                }

                Players players = new Players((playerDAO.cercarIdPerNom(jugadorNom)),nom,cognom, Date.valueOf("2000-05-02"),"180.6","90.5", "07","Forward",(equipDAO.cercarIdPerNom(equipNom)));

                boolean correcte = playerDAO.insert(players);

                //Comprovar si s'ha creat correctament.
                if (correcte) System.out.println("El jugador s'ha registrat correctament");
                else System.out.println("El jugador no s'ha registrat correctament");
            } else {
                System.out.println("L'equip no existeix.");
            }
        } else { return true; }
        return false;
    }


    //5.- Traspassar un judador a un altra equip
    public static void exercici5(String jugadorNom, String equipNom) throws Exception {
        int jugadorId = playerDAO.cercarIdPerNom(jugadorNom);

        if (jugadorId != 0) {
            int equipId = equipDAO.cercarIdPerNom(equipNom);

            if (equipId != 0) {
                Players players = playerDAO.cercar(jugadorId);
                players.setEquipId(equipId);

                boolean correcte = playerDAO.update(players);

                if (correcte) {
                    System.out.println("S'ha traspassat correctament");
                } else {
                    System.out.println("No s'ha traspassat el jugador");
                }
            } else { System.out.println("L'equip no existeix"); }
        } else { System.out.println("El jugador no existeix."); }
    }


    //6.- Actualitzar les dades de jugadors o equips després d'un partit.


    //7.- Modificar les estadístiques d’un jugador


    //8.- Retirar (Eliminar) un jugador.


    //9.- Canviar nom franquícia d’un equip



}
