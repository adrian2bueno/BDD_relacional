package Controlador;

import Exercicis.Exercicis;
import Model.DAO.TeamsDAO;
import Model.*;
import Vista.Vista;

import java.util.Scanner;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    //DAO
    static TeamsDAO equipDAO = new TeamsDAO();

    //VARIABLES:
    //Opcio Menu
    public static String opcio = "";

    //Variable per rebre el nom de l'equip.
    public static String equipNom;

    //Variable per rebre el nom del jugador.
    public static String jugadorNom;

    public static void consultas() throws Exception {
        try {
            do {
                //Canviem el limitador de scan perquè agafi espais. (Per evitar problemes amb els espais dels noms)
                scan.useDelimiter("\n");
                //Cridem al menú
                Vista.menuInicial();
                opcio = scan.nextLine();
                switch (opcio) {
                    case "1":
                        System.out.println("Introdueix el nom d'un equip per llistar els seus jugadors: palomera");
                        equipNom = scan.nextLine();
                        Exercicis.exercici1(equipNom);

                        break;
                    case "2":
                        jugadorNom = scan.nextLine();
                        Exercicis.exercici2(jugadorNom);
                        break;
                    case "3":
                        equipNom = scan.nextLine();
                        Exercicis.exercici3(equipNom);
                        break;
                    case "4":
                        System.out.println("Introdueix un jugador per inserir a la taula: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        System.out.println("Introdueix un equip on unir a aquest jugador: (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();

                        //Cridem l'excercici 4
                        boolean jaExisteix = Exercicis.exercici4(jugadorNom,equipNom);

                        //Si el jugador ja existeix, preguntem a l'usuari si vol traspassar-lo o no.
                        if (jaExisteix) {
                            System.out.println("El jugador ja existeix, vols traspassar-lo a un altre equip? (S/n)");
                            //Variable Si/No per preguntar al usuari.
                            String opcioSiNo;
                            opcioSiNo = scan.nextLine().toUpperCase();
                            if (opcioSiNo.equals("S") || opcioSiNo.isEmpty()) {
                                Exercicis.exercici5(jugadorNom, equipNom);
                            }
                        }
                        break;
                    case "5":
                        System.out.println("Introdueix un jugador per trasspasar a un altre equip: (Ex: LeBron James)");
                        jugadorNom = scan.nextLine();
                        System.out.println("Introdueix un equip on trasspasar aquest jugador (Ex: Denver Nuggets)");
                        equipNom = scan.nextLine();
                        //Cridem l'excercici 5
                        Exercicis.exercici5(jugadorNom,equipNom);
                        break;
                    case "6":

                        break;
                    case "7":

                        break;
                    case "8":

                        break;
                    case "9":

                        break;
                    case "0":
                        break;
                    default:
                        System.out.println("-------------------------------");
                        System.out.println("ATENCIÓ! Ha de ser entre 0 i 3");
                        System.out.println("-------------------------------");
                }
            } while (!(opcio.equals("0")));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
