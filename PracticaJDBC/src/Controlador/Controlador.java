package Controlador;

import Model.Dao.*;
import Model.Model;
import Model.Team;
import Model.Player;
import Vista.Vista;
import Model.HistoricPlayers;
import Model.Players_stats;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Controlador {
    static Scanner scan = new Scanner(System.in);

    // Llista els jugadors d'un equip introduït comprovant primer si el nom és correcte
    public static void exercici1(String nom) {
        if (verificarNom(nom)) {
            PlayerDAO player = new PlayerDAO();
            List<Player> llista = player.llistarJugadors(nom);

            System.out.printf("%-15s%-9s%-9s", "Nom", "Alçada", "Pes");
            System.out.println();
            //Mostra la llista de jugadors
            for (Player players : llista) {
                System.out.printf("%-15s%-9d%-9d\n", players.getNom(), players.getAlcada(), players.getPes());
            }
        } else {
            System.out.println("El nom es incorrecte");
        }
    }

    // Mostra les estadístiques mitjanes del jugador introduït comprovant primer si el nom és correcte
    public static void exercici2(String nom) {
        if (verificarNom(nom)) {
            PlayerDAO player = new PlayerDAO();
            List<Players_stats> lista = player.medianasPlayers(nom);

            System.out.printf("%-18s%-18s%-18s", "Mitjana punts", "Mitjana rebots", "Mitjana assistències");
            System.out.println();
            //Mostra la llista de jugadors
            for (Players_stats players : lista) {
                System.out.printf("%-18.2f%-18.2f%-18.2f\n", players.getAvg_puntos(), players.getAvg_rebotes(), players.getAvg_asistencias());
            }
        } else {
            System.out.println("El nom es incorrecte");
        }
    }

    // Mostra els resultats de l'equip passat comprovant primer si el nom és correcte
    public static void exercici3(String nom) {
        if (verificarNom(nom)) {
            MatchDAO equip = new MatchDAO();

            //Mostra els partits jugats per l’equip donat amb un SELECT que agafa els resultats
            // dels partits que involucren un equip específic de la base de dades.
            // Concatena els noms dels equips visitants i locals amb els seus respectius punts
            // en una sola cadena per a cada partit.
            List<String> partits = equip.resultPartits(nom);
            System.out.println("Llista de partits");
            for (String element : partits) {
                System.out.println(element);
            }

        } else {
            System.out.println("El nom es incorrecte");
        }
    }

    //Insereix un jugador nou si no és a la base de dades, si està sol·licita confirmació de traspàs
    public static void exercici4() throws SQLException {
        String nom;
        boolean correcto;
        PlayerDAO db = new PlayerDAO();
        Player nPlayer = new Player();
        int id_equipActual;
        String equip_actual;
        String opt = ("1- Si 2- No 3- Sortir");

        System.out.print("Nom del jugador: ");
        nom = scan.nextLine();

//si el nom és un text, i no és a la base de dades (la select dóna -1),
//// Afegeix a l'objecte player el nom. Si no (la select no dóna -1), aquest jugador ja existeix i es truca a traspassar
        try {
            if (verificarNom(nom)) {
                if (Model.obtenerIdJugador(nom) == -1) {
                    nPlayer.setNom(nom);

                    System.out.print("Altura del jugador: ");
                    int alcada = scan.nextInt();
                    nPlayer.setAlcada(alcada);

                    System.out.print("Pes del jugador: ");
                    int pes = scan.nextInt();
                    nPlayer.setAlcada(pes);
                    scan.nextLine();
                    do {
                        // hasta que el equipo no sea correcto no deja de preguntar
                        correcto = true;
                        try {
                            System.out.print("Equip del jugador: ");
                            equip_actual = scan.nextLine();

                            if (verificarNom(equip_actual)) {
                                id_equipActual = Model.obtenerIdEquip(equip_actual);
                                if (id_equipActual == -1) {
                                    throw new IllegalArgumentException("Aquest equip no existeix");
                                } else {
                                    nPlayer.setEquip_actual(id_equipActual);
                                }

                                if (db.insert(nPlayer)) {
                                    System.out.println("El jugador ha estat creat i assignat a l'equip");
                                } else {
                                    System.out.println("Hi ha hagut un error creant el jugador");
                                }
                            } else {
                                throw new IllegalArgumentException("El equip no es correcto");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            correcto = false;
                        }

                    } while (!correcto);

                } else {
                    System.out.println("Aquest jugador ja existeix a la base de dades");

                    System.out.println("Vols traspasarlo? ");
                    System.out.println(opt);
                    int o = scan.nextInt();
                    // switch de les opcions
                    switch (o) {
                        case 1:
                            exercici5(true);

                            break;
                        case 2:
                            exercici4();
                            break;
                        case 3:
                            Menu.menuPrincipal();
                            break;
                    }
                }
            } else {
                throw new IllegalArgumentException("El nom no és correcte");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void exercici5(boolean traspas_equip) throws SQLException {
        String nom;
        String equipN;
        Player player = new Player();
        int equipo_nuevo;
        PlayerDAO db = new PlayerDAO();
        String opt = ("1- Si 2- No 3- Sortir");

        if (!traspas_equip) {
            do {
                System.out.println("Nom del jugador: ");
                nom = scan.nextLine();
                if (!verificarNom(nom)) {
                    System.out.println("El nom no es correcte");
                }
            } while (!verificarNom(nom));

        } else {
            System.out.println("Confirma el nom: ");
            nom = scan.nextLine();
        }


        if (verificarNom(nom)) {
            int id_jugador = Model.obtenerIdJugador(nom);
            if (id_jugador == -1) {
                player.setId(Model.obtenerIdJugador(nom));
                player = db.llegir(player);
                Vista.mostrarGeneric(player, true);
                System.out.println();

                System.out.println("És aquest el jugador? ");
                System.out.println(opt);
                int o = scan.nextInt();
                switch (o) {
                    case 1:
                        System.out.println("Introdueix el nou equip: ");
                        equipN = scan.nextLine();
                        equipo_nuevo = Model.obtenerIdJugador(equipN);
                        player.setEquip_actual(equipo_nuevo);
                        if (db.update(player)) {
                            System.out.println("Traspasat correctament");
                        } else {
                            System.out.println("Hi ha hagut un error al traspàs");
                        }
                        break;
                    case 2:
                        exercici5(false);
                        break;

                    default:
                        Menu.menuPrincipal();
                }
                Menu.menuPrincipal();
            } else {
                player.setId(id_jugador);
                player = db.llegir(player);
                Vista.mostrarGeneric(player, true);
                System.out.println();
                // menu para confirmar que se traspasa el jugador correcto
                // 1 proceso de traspaso, 2 introducir de vuelta un nuevo jugador y 3 salir
                System.out.println("És aquest el jugador? ");
                System.out.println(opt);
                int o = scan.nextInt();
                switch (o) {
                    case 1:
                        System.out.println("Introdueix el nou equip: ");
                        equipN = scan.nextLine();
                        equipo_nuevo = Model.obtenerIdEquip(equipN);
                        player.setEquip_actual(equipo_nuevo);
                        if (db.update(player)) {
                            System.out.println("Traspasat correctamente");
                        } else {
                            System.out.println("Hi ha hagut un error al traspàs");
                        }
                        break;
                    case 2:
                        exercici5(false);
                        break;

                    default:
                        Menu.menuPrincipal();
                }
            }
        } else {
            System.out.println("El nom no és correcte");
            Menu.menuPrincipal();
        }
    }


    public static void exercici8() throws SQLException {
        String nom;
        HistoricPlayers retirado;
        String nom_equip;
        String opt = ("1- Si 2- No 3- Sortir");
        int id;
        Player playerRet = new Player();
        PlayerDAO dbp = new PlayerDAO();
        Players_stats ps = new Players_stats();
        PlayerStatsDAO dbs = new PlayerStatsDAO();
        HistoricPlayersDAO db = new HistoricPlayersDAO();

        do {
            System.out.println("Introdueix el jugador que vols retirar: ");
            nom = scan.nextLine().trim();
            if (!verificarNom(nom)) {
                System.out.println("El nom no és correcte");
            }
        } while (!verificarNom(nom));

        if (Model.obtenerIdJugador(nom) == -1) {
            id = Model.obtenerIdJugador(nom);
            playerRet.setId(id);
            ps.setId_jugador(id);

            playerRet = dbp.llegir(playerRet);  // dades del jugador
            ps = dbs.llegir(ps);        // stats del jugador

            Vista.mostrarGeneric(playerRet, true);
            System.out.println();

            // confirmació que el jugador és correcte
            // 1 procés de retirar, 2 torna a preguntar un jugador i 3 o qualsevol número
            System.out.println("És aquest el jugador? ");
            System.out.println(opt);
            int o = scan.nextInt();
            switch (o) {
                case 1:
                    // Només es fa el procés si hi ha un jugador per retirar
                    if (ps != null && playerRet != null) {
                        //Accedir a les dades de player stats, obtenir l'id, els stats i el nom de l'últim equip de players
                        nom_equip = Model.obtenerNombreEquipo(playerRet.getEquip_actual());
                        retirado = new HistoricPlayers(playerRet.getId(), ps.getAvg_puntos(), ps.getAvg_rebotes(), ps.getAvg_asistencias(), nom_equip);

                        // Eliminar de player stats
                        if (db.insert(retirado)) {
                            dbs.delete(ps);
                            dbp.delete(playerRet);
                            System.out.println("Jugador retirado con exito!");
                            Menu.menuPrincipal();
                        }
                    } else {
                        exercici8();
                    }
                case 2:
                    exercici8();

                default:
                    Menu.menuPrincipal();
            }
            Menu.menuPrincipal();
        } else {
            id = Model.obtenerIdJugador(nom);

            playerRet.setId(id);
            ps.setId_jugador(id);

            playerRet = dbp.llegir(playerRet);  // dades del jugador
            ps = dbs.llegir(ps);        // stats del jugador

            Vista.mostrarGeneric(playerRet, true);
            System.out.println();

            // confirmació que el jugador és correcte
            // 1 procés de retirar, 2 torna a preguntar un jugador i 3 o qualsevol número més menu principal
            System.out.println("És aquest el jugador? ");
            System.out.println(opt);
            int o = scan.nextInt();
            switch (o) {
                case 1:
                    if (ps != null && playerRet != null) {
                        nom_equip = Model.obtenerNombreEquipo(playerRet.getEquip_actual());
                        retirado = new HistoricPlayers(playerRet.getId(), ps.getAvg_puntos(), ps.getAvg_rebotes(), ps.getAvg_asistencias(), nom_equip);

                        if (db.insert(retirado)) {
                            dbs.delete(ps);
                            dbp.delete(playerRet);
                            System.out.println("Jugador retirado con exito!");
                            Menu.menuPrincipal();
                        }
                    } else {
                        exercici8();
                    }
                case 2:
                    exercici8();

                default:
                    Menu.menuPrincipal();
            }
        }

    }

    //Funció per canviar la franquícia a partir de la funció d'IntroduirFranquícia
    public static void exercici9() throws SQLException {
        int id_equipo;
        Team equipo = new Team();
        TeamDAO dbt = new TeamDAO();
        String nombre, franquicia;
        do {
            System.out.println("Quina franquícia vols canviar (Ej: Los Angeles Lakers): ");
            nombre = scan.nextLine();
            if (!verificarNom(nombre)) {
                System.out.println("El nom no és correcte");
            }
        } while (!verificarNom(nombre));

        id_equipo = Model.obtenerIdEquipoNomComplet(nombre);
        if (id_equipo == -1) {
            do {
                System.out.println("Escriu la franquicia: ");
                franquicia = scan.nextLine();
                if (!verificarNom(franquicia)) {
                    System.out.println("El nom no és correcte");
                }
            } while (!verificarNom(franquicia));
            // Faig que la primera lletra sigui majúscula
            String franquiciaNueva = franquicia.substring(0, 1).toUpperCase() + franquicia.substring(1).toLowerCase();
            equipo.setId(Model.obtenerIdEquipoNomComplet(nombre));
            equipo = dbt.llegir(equipo);
            equipo.setFranquicia(franquiciaNueva);
            if (dbt.update(equipo)) {
                System.out.println("Modificado amb exit");
            } else {
                System.out.println("No s'ha pogut modificar");
            }
            Menu.menuPrincipal();
        }
        Menu.menuPrincipal();
    }

    private static boolean verificarNom(String nom) {
        return nom.matches("^[a-zA-Z]+([-' ][a-zA-Z]+)*$");
    }
}
