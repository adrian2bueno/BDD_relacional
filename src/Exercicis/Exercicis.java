package Exercicis;

import Model.Jugador;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Exercicis {
    //1.- Llistar tots els jugadors d'un equip



    public static void llistarJugadorsEquip(List<Jugador> jugadors){
        for (Jugador jugador : jugadors) {
            System.out.println(jugador.getNom());
        }
    }
    //2.- Calcular la mitjana de punts, rebots, assistències, ... d'un jugador


    //3.- Llistar tots els partits jugats per un equip amb el seu resultat.
    public static void llistarPartitsIResultats(List<Set<Map.Entry<String,Integer>>> llista){
        for (Set<Map.Entry<String,Integer>> set : llista) {
            for (Map.Entry<String,Integer> entry : set) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }
    }

    //4.- Inserir un nou jugador a un equip.


    //5.- Traspassar un judador a un altra equip


    //6.- Actualitzar les dades de jugadors o equips després d'un partit.


    //7.- Modificar les estadístiques d’un jugador


    //8.- Retirar (Eliminar) un jugador.


    //9.- Canviar nom franquícia d’un equip



}
