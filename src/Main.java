import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //--------------------------------------------------------------------------------------------------------------
        // Scanners
        //--------------------------------------------------------------------------------------------------------------

        Scanner r = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        //--------------------------------------------------------------------------------------------------------------
        // Definición de las variables
        //--------------------------------------------------------------------------------------------------------------

        int raiInicial = 0;
        int sacInicial = 0;

        int reajusteRai = 0;
        int reajusteSac = 0;

        int raiInicialReajustado = 0;
        int sacInicialReajustado = 0;

        //--------------------------------------------------------------------------------------------------------------

        int raiReversado = 0;

        int raiEjercicio = 0;
        int sacEjercicio = 0;

        int raiAntesDeDistribuciones = 0;
        int sacAntesDeDistribuciones = 0;

        String mes = "";

        String [] distribuciones = new String[12];
        int distribucionesTotales = 0;

        int sacCastigado = 0;

        int raiRemanente = 0;
        int sacRemanente = 0;

        double reajusteRre = 0.133;

        //--------------------------------------------------------------------------------------------------------------
        // Main Loop
        //--------------------------------------------------------------------------------------------------------------
        Textos.intro();
        int respuesta = 6;

        while (respuesta != 0) {
            Textos.menu();

            respuesta = Integer.parseInt(r.nextLine());

            switch (respuesta) {
                case 0:
                    System.out.println("Ha salido de la aplicación");
                    break;

                case 1:
                    System.out.println("Seleccionó ingresar datos iniciales");

                    System.out.println("Ingrese RAI de apertura (01.01.2022)");
                    raiInicial = Integer.valueOf(sc.nextLine());

                    System.out.println("Ingrese SAC apertura (01.01.2022)");
                    sacInicial = Integer.valueOf(sc.nextLine());

                    System.out.println("se ingresó RAI Inicial (01.01.2022)>>> "+raiInicial);
                    System.out.println("se ingresó SAC inicial (01.01.2022)>>> "+sacInicial);

                    break;

                case 2:
                    System.out.println("Seleccionó ingresar RAI y SAC al 31.12.2022");

                    System.out.println("Ingrese RAI final");
                    raiEjercicio = Integer.valueOf(sc.nextLine());
                    System.out.println("ingresó>>> "+raiEjercicio);

                    System.out.println("Ingrese SAC del ejercicio generado en 2022");
                    sacEjercicio = Integer.valueOf(sc.nextLine());
                    System.out.println("ingresó>>> "+sacEjercicio);

                    break;

                case 3:
                    System.out.println("Seleccionó ingresar distribuciones");

                    for( int i = 0; i <= 11; i++){
                        System.out.println("Distribuciones (históricas) Mes: "+(i+1)+"/2022");
                        distribuciones[i] = String.valueOf(Integer.valueOf(sc.nextLine()));
                        System.out.println(mes+" "+ (i+1) + " "+distribuciones[i]);
                    }

                    break;
                case 4:
                    System.out.println("Seleccionó ingresar GRNG");
                    // Función que deduce los GRNG
                    break;
                case 5:
                    System.out.println("Seleccionó generar RRE");
                    // Función generadora de RRE

                    // 1. Reajustes
                    raiInicialReajustado = (int) Math.ceil(raiInicial * (1 + reajusteRre));
                    sacInicialReajustado = (int) Math.ceil(sacInicial * (1 + reajusteRre));

                    reajusteRai = raiInicialReajustado - raiInicial;
                    reajusteSac = sacInicialReajustado - sacInicial;

                    //2. Reverso del RAI
                    raiReversado = raiInicialReajustado;

                    //3. RAI generado en el año
                    // >>>> ok

                    //4. SAC generado en el año
                    // >>>> ok

                    //5. Saldo antes de distribuciones
                    raiAntesDeDistribuciones = raiEjercicio;
                    sacAntesDeDistribuciones =  sacInicialReajustado + sacEjercicio;

                    //6. Procesar distribuciones
                    // sumar las distribuciones con un for
                    for(int j = 0; j <= 11; j++ ){
                        int dist = Integer.parseInt(String.valueOf(distribuciones[j]));
                        distribucionesTotales += dist;
                    }


                    int sacImputado = (int) Math.ceil(distribucionesTotales * 0.369863);

                    if(sacImputado > sacAntesDeDistribuciones){
                        sacImputado = sacAntesDeDistribuciones;
                    }

                    sacRemanente = sacAntesDeDistribuciones - sacImputado;

                    // situación del RAI

                    int distribuciones_no_imputadas = 0;

                    raiRemanente = raiAntesDeDistribuciones - distribucionesTotales;

                    if(raiRemanente < 0 ){
                        distribuciones_no_imputadas = Math.abs(raiRemanente);
                        raiRemanente = 0;
                        distribucionesTotales = raiRemanente;
                    }



                    // Reporte final
                    System.out.println("DETALLE.............................."+Funciones.rellenar("RAI")+Funciones.rellenar("SAC"));
                    System.out.println("Saldos de apertura..................."+Funciones.rellenar(String.valueOf(raiInicial))+Funciones.rellenar(String.valueOf(sacInicial)));
                    System.out.println("Reajustes (13.3%)...................."+Funciones.rellenar(String.valueOf(reajusteRai))+Funciones.rellenar(String.valueOf(reajusteSac)));
                    System.out.println("Saldos reajustados..................."+Funciones.rellenar(String.valueOf(raiInicialReajustado))+Funciones.rellenar(String.valueOf(raiInicialReajustado)));
                    System.out.println("Reverso del RAI......................"+Funciones.rellenar(String.valueOf(-raiReversado)));
                    System.out.println("RAI del ejercicio...................."+Funciones.rellenar(String.valueOf(raiEjercicio)));
                    System.out.println("SAC del ejercicio...................."+Funciones.rellenar(" ")+Funciones.rellenar(String.valueOf(sacEjercicio)));
                    System.out.println("Saldos antes de distribuciones......."+Funciones.rellenar(String.valueOf(raiAntesDeDistribuciones))+Funciones.rellenar(String.valueOf(sacAntesDeDistribuciones)));
                    System.out.println("Distribuciones......................."+Funciones.rellenar(String.valueOf(distribucionesTotales))+Funciones.rellenar(String.valueOf(sacImputado)));
                    System.out.println("Saldo Remanente......................"+Funciones.rellenar(String.valueOf(raiRemanente))+Funciones.rellenar(String.valueOf(sacRemanente)));
                    System.out.println("Dist. no Imp. (Cod. 1193, F22)......."+Funciones.rellenar(String.valueOf(distribuciones_no_imputadas)));

                    System.out.println("*******");

                    for(int j = 0; j <= 11; j++ ){
                        System.out.println("Mes: "+(j+1)+": "+ distribuciones[j]);
                    }

                    break;

                default:
                    System.out.println("Ingresa una respuesta válida");
                    break;
            }

            Textos.limpiar();
        }
    }
}