import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //--------------------------------------------------------------------------------------------------------------
        // Scanners
        //--------------------------------------------------------------------------------------------------------------

        Scanner r = new Scanner(System.in);

        //--------------------------------------------------------------------------------------------------------------
        // Registro de rentas empresariales y su inicialización
        //--------------------------------------------------------------------------------------------------------------

        // Instancia el manejador
        Manejador manejador = new Manejador();
        manejador.instanciarLibros();

        //--------------------------------------------------------------------------------------------------------------
        // Main Loop (until close by: user)
        //--------------------------------------------------------------------------------------------------------------

        Textos.intro();

        int respuesta;

        do {
            Textos.menu();

            respuesta = Integer.parseInt(r.nextLine());

            switch (respuesta) {
                case 0:
                    Textos.salir();
                    break;
                case 1:
                    manejador.response_case1();
                    break;
                case 2:
                    manejador.response_case2();
                    break;
                case 3:
                    manejador.response_case3();
                    break;
                case 4:
                    manejador.response_case4();
                    break;
                case 5:
                    manejador.response_case5();
                    break;
                default:
                    manejador.response_case_default();
                    break;
            }
        }while (respuesta != 0);
    }
}