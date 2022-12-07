import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner r = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        Textos.intro();
        int respuesta = 3;

        while (respuesta != 0) {
            Textos.menu();

            respuesta = Integer.parseInt(r.nextLine());

            switch (respuesta) {
                case 0:
                    System.out.println("Ha salido de la aplicación");
                    break;
                case 1:
                    System.out.println("Seleccionó ingresar datos iniciales");
                    // Función que agrega:
                    // RAI
                    // SAC
                    break;
                case 2:
                    System.out.println("Seleccionó ingresar RAI");
                    // Función que agrega:
                    // RAI al cierre
                    break;
                case 3:
                    System.out.println("Seleccionó ingresar distribuciones");
                    // Función que agrega:
                    // Distribuciones de enero a diciembre
                    break;
                case 4:
                    System.out.println("Seleccionó ingresar GRNG");
                    // Función que deduce los GRNG
                    break;
                case 5:
                    System.out.println("Seleccionó generar RRE");
                    // Función generadora de RRE
                    break;
                default:
                    System.out.println("Ingresa una respuesta válida");
            }
        }
    }
}

/*
        System.out.println("Ingrese RAI de apertura (01.01.2022)");
        int rai = Integer.valueOf(sc.nextLine());
        System.out.println("ingresó "+rai);

        System.out.println("Ingrese SAC apertura (01.01.2022");
        int sac = Integer.valueOf(sc.nextLine());
        System.out.println("ingresó "+sac);

        System.out.println("Ingrese distribuciones DIC-2022");
        int distribuciones = Integer.valueOf(sc.nextLine());
        System.out.println("ingresó "+distribuciones);


        //-----------------------------------
        // Reajustes
        //-----------------------------------
        double reajuste = 0.133;

        int raiReajustado;
        int sacReajustado;
        int cmRai;
        int cmSac;

        raiReajustado = (int) Math.ceil(rai * (1 + reajuste));
        sacReajustado = (int) Math.ceil(sac * (1 + reajuste));

        cmRai = raiReajustado - rai;
        cmSac = sacReajustado - sac;


        //-----------------------------------
        // Imputación de retiros al SAC
        //-----------------------------------

        int sacImputado = (int) Math.ceil(distribuciones * 0.369863);

        if(sacImputado > sac){
            sacImputado = sac;
        }

        int sacRemanente = sac - sacImputado;

        //-----------------------------------
        // Imputación de retiros al RAI
        //-----------------------------------
        int raiRemanente;
        int distribuciones_no_imputadas = 0;

        raiRemanente = rai - distribuciones;

        if(raiRemanente < 0 ){
            distribuciones_no_imputadas = Math.abs(raiRemanente);
            raiRemanente = 0;
            distribuciones = rai;
        }

        System.out.println("DETALLE.............................."+Funciones.rellenar("RAI")+Funciones.rellenar("SAC"));
        System.out.println("Saldos de apertura..................."+Funciones.rellenar(String.valueOf(rai))+Funciones.rellenar(String.valueOf(sac)));
        System.out.println("Reajustes (13.3%)...................."+Funciones.rellenar(String.valueOf(cmRai))+Funciones.rellenar(String.valueOf(cmSac)));
        System.out.println("Saldos reajustados..................."+Funciones.rellenar(String.valueOf(raiReajustado))+Funciones.rellenar(String.valueOf(sacReajustado)));
        System.out.println("Distribuciones......................."+Funciones.rellenar(String.valueOf(distribuciones))+Funciones.rellenar(String.valueOf(sacImputado)));
        System.out.println("Saldo Remanente......................"+Funciones.rellenar(String.valueOf(raiRemanente))+Funciones.rellenar(String.valueOf(sacRemanente)));
        System.out.println("Dist. no Imp. (Cod. 1193, F22)......."+Funciones.rellenar(String.valueOf(distribuciones_no_imputadas)));
    }
}
*/