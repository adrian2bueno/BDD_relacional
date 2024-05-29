package Controlador;

import Vista.Vista;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    static Scanner scan = new Scanner(System.in);
    //Variable per rebre el nom de l'equip.
    public static String equipNom;

    //Variable per rebre el nom del jugador.
    public static String jugadorNom;
    public static void menuPrincipal() throws SQLException {
        int opcio;
        try {
            do {
                //Cridem al menú
                Vista.menuInicial();

                System.out.print("Quina opció vols escollir: ");
                opcio = scan.nextInt();
                scan.nextLine();


                switch (opcio) {
                    case 1:
                        System.out.print("Introdueix el nom d'un equip per llistar els seus jugadors: ");
                        equipNom = scan.nextLine();
                        Controlador.exercici1(equipNom);

                        break;
                    case 2:
                        System.out.println("Introdueix el nom complet d'un jugador per calcular les seves mitjanes: Ex: LeBron James");
                        jugadorNom = scan.nextLine();
                        Controlador.exercici2(jugadorNom);

                        break;
                    case 3:
                        System.out.print("Introdueix el nom d'un equip per llistar tots els partits jugats: ");
                        equipNom = scan.nextLine();
                        Controlador.exercici3(equipNom);

                        break;
                    case 4:
                        Controlador.exercici4();

                        break;
                    case 5:
                        Controlador.exercici5(false);

                        break;
                    case 6:
//TODO: NO FET
                        break;
                    case 7:
//TODO: NO FET
                        break;
                    case 8:
                        Controlador.exercici8();

                        break;
                    case 9:
                        Controlador.exercici9();
                        break;
                    case 0:
                        System.out.println("Sortint de la aplicació");
                        break;
                    default:
                        System.out.println("ATENCIÓ! Ha de ser entre 0 i 9");
                }
                System.out.println();
            } while (!(opcio == 0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


}
