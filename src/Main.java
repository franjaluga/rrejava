import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //--------------------------------------------------------------------------------------------------------------
        // Scanners
        //--------------------------------------------------------------------------------------------------------------

        Scanner r = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        //--------------------------------------------------------------------------------------------------------------
        // Registro de rentas empresariales y su inicialización
        //--------------------------------------------------------------------------------------------------------------

        Registro rre = new Registro();
        rre.inicializar();

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
                    rre.raiInicial = Integer.parseInt(sc.nextLine());

                    System.out.println("Ingrese SAC apertura (01.01.2022)");
                    rre.sacInicial = Integer.parseInt(sc.nextLine());

                    System.out.println("se ingresó RAI Inicial (01.01.2022)>>> "+rre.raiInicial);
                    System.out.println("se ingresó SAC inicial (01.01.2022)>>> "+rre.sacInicial);

                    break;

                case 2:
                    System.out.println("Seleccionó ingresar RAI y SAC al 31.12.2022");

                    System.out.println("Ingrese RAI final");
                    rre.raiEjercicio = Integer.parseInt(sc.nextLine());
                    System.out.println("ingresó>>> "+rre.raiEjercicio);

                    System.out.println("Ingrese SAC del ejercicio generado en 2022");
                    rre.sacEjercicio = Integer.parseInt(sc.nextLine());
                    System.out.println("ingresó>>> "+rre.sacEjercicio);

                    break;

                case 3:
                    System.out.println("Seleccionó ingresar distribuciones");

                    for( int i = 0; i <= 11; i++){
                        System.out.println("Distribuciones (históricas) Mes: "+(i+1)+"/2022");
                        rre.distribuciones[i] = String.valueOf(Integer.valueOf(sc.nextLine()));
                        System.out.println(rre.mes+" "+ (i+1) + " "+rre.distribuciones[i]);
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
                    rre.raiInicialReajustado = (int) Math.ceil(rre.raiInicial * (1 + rre.reajusteRre));
                    rre.sacInicialReajustado = (int) Math.ceil(rre.sacInicial * (1 + rre.reajusteRre));

                    rre.reajusteRai = rre.raiInicialReajustado - rre.raiInicial;
                    rre.reajusteSac = rre.sacInicialReajustado - rre.sacInicial;

                    //2. Reverso del RAI
                    rre.raiReversado = rre.raiInicialReajustado;

                    //3. RAI generado en el año
                    // >>>> ok

                    //4. SAC generado en el año
                    // >>>> ok

                    //5. Saldo antes de distribuciones
                    rre.raiAntesDeDistribuciones = rre.raiEjercicio;
                    rre.sacAntesDeDistribuciones =  rre.sacInicialReajustado + rre.sacEjercicio;

                    //6. Procesar distribuciones
                    // sumar las distribuciones con un for
                    for(int j = 0; j <= 11; j++ ){
                        int dist = Integer.parseInt(String.valueOf(rre.distribuciones[j]));
                        rre.distribucionesTotales += dist;
                    }


                    int sacImputado = (int) Math.ceil(rre.distribucionesTotales * 0.369863);

                    if(sacImputado > rre.sacAntesDeDistribuciones){
                        sacImputado = rre.sacAntesDeDistribuciones;
                    }

                    rre.sacRemanente = rre.sacAntesDeDistribuciones - sacImputado;

                    // situación del RAI



                    rre.raiRemanente = rre.raiAntesDeDistribuciones - rre.distribucionesTotales;

                    if(rre.raiRemanente < 0 ){
                        rre.distribuciones_no_imputadas = Math.abs(rre.raiRemanente);
                        rre.raiRemanente = 0;
                        rre.distribucionesTotales = rre.raiRemanente;
                    }

                    /*
                    // Reporte final
                    System.out.println("DETALLE.............................."+Funciones.rellenar("RAI")+Funciones.rellenar("SAC"));
                    System.out.println("Saldos de apertura..................."+Funciones.rellenar(String.valueOf(rre.raiInicial))+Funciones.rellenar(String.valueOf(rre.sacInicial)));
                    System.out.println("Reajustes (13.3%)...................."+Funciones.rellenar(String.valueOf(rre.reajusteRai))+Funciones.rellenar(String.valueOf(rre.reajusteSac)));
                    System.out.println("Saldos reajustados..................."+Funciones.rellenar(String.valueOf(rre.raiInicialReajustado))+Funciones.rellenar(String.valueOf(rre.raiInicialReajustado)));
                    System.out.println("Reverso del RAI......................"+Funciones.rellenar(String.valueOf(-rre.raiReversado)));
                    System.out.println("RAI del ejercicio...................."+Funciones.rellenar(String.valueOf(rre.raiEjercicio)));
                    System.out.println("SAC del ejercicio...................."+Funciones.rellenar(" ")+Funciones.rellenar(String.valueOf(rre.sacEjercicio)));
                    System.out.println("Saldos antes de distribuciones......."+Funciones.rellenar(String.valueOf(rre.raiAntesDeDistribuciones))+Funciones.rellenar(String.valueOf(rre.sacAntesDeDistribuciones)));
                    System.out.println("Distribuciones......................."+Funciones.rellenar(String.valueOf(rre.distribucionesTotales))+Funciones.rellenar(String.valueOf(sacImputado)));
                    System.out.println("Saldo Remanente......................"+Funciones.rellenar(String.valueOf(rre.raiRemanente))+Funciones.rellenar(String.valueOf(rre.sacRemanente)));
                    System.out.println("Dist. no Imp. (Cod. 1193, F22)......."+Funciones.rellenar(String.valueOf(rre.distribuciones_no_imputadas)));

                    System.out.println("*******");

                    for(int j = 0; j <= 11; j++ ){
                        System.out.println("Mes: "+(j+1)+": "+ rre.distribuciones[j]);
                    }
                    */
                    break;

                default:
                    System.out.println("Ingresa una respuesta válida");
                    break;
            }
        }
    }
}