import com.sun.source.tree.DoWhileLoopTree;

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

        Registro rre = new Registro();
        rre.inicializar();

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
                    rre.response_case1();
                    break;
                case 2:
                    rre.response_case2();
                    break;
                case 3:
                    rre.response_case3();
                    break;
                case 4:
                    rre.response_case4();
                    break;
                case 5:
                    rre.response_case5();
                    break;
                default:
                    rre.response_case_default();
                    break;
            }
        }while (respuesta != 0);
    }
}