import Controlador.Menu;

public class Main {
    public static void main(final String[] args) {
        try{
            //Crida del menú principal
            Menu.menuPrincipal();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
