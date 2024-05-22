package Controlador;

import Model.DAO.TeamsDAO;
import Model.*;
import Vista.Vista;

import java.util.List;
import java.util.Scanner;

public class Controlador {
    static Scanner scan = new Scanner(System.in);
    //DAO
    static TeamsDAO equipDAO = new TeamsDAO();

    //VARIABLES:
    //Opcio Menu
    public static String opcio = "";

    //Variable per rebre el nom de l'equip.
    static String equipNom;

    //Variable per rebre el nom del jugador.
    public String jugadorNom;

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
                        equipNom = scan.nextLine();
                        List<Jugador> jugadors = equipDAO.obtenirJugadors(equipNom);
                        Vista.llistarJugadorsEquip(jugadors);

                        break;
                    case "2":
                        System.out.println("Introdueix un jugador per calcular la seva mitjana de punts: (Ex: LeBron James)");

                        break;
                    case "3":
                        break;
                    case "4":
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
