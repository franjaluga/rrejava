import java.util.Scanner;

public class TaxBook{
    Rai rai = new Rai();
    Sac sac = new Sac();
    Dis dist =  new Dis();

    Scanner consoleUserSubMenuResponse = new Scanner(System.in);

    public void createBook(){
        rai.inicializar();
        sac.inicializar();
        dist.inicializar();
    }

    public void case1(){
        System.out.println("Seleccionó ingresar datos iniciales");
        System.out.println("Ingrese RAI de apertura (01.01.2022)");
        rai.setSaldoInicial(Integer.parseInt(consoleUserSubMenuResponse.nextLine()));

        System.out.println("Ingrese SAC apertura (01.01.2022)");
        sac.setSaldoInicial(Integer.parseInt(consoleUserSubMenuResponse.nextLine()));


        rai.setSaldoReajustado((int) Math.ceil(rai.saldoInicial * (1 + Constantes.CM_22)));
        sac.setSaldoReajustado((int) Math.ceil(sac.saldoInicial * (1 + Constantes.CM_22)));

        rai.setReajuste(rai.getSaldoReajustado() - rai.getSaldoInicial());
        sac.setReajuste(sac.getSaldoReajustado() - sac.getSaldoInicial());

        updateAndPrintBalance();

    }

    public void case2(){
        System.out.println("Seleccionó ingresar RAI y SAC al 31.12.2022");
        System.out.println("Ingrese RAI final");
        rai.setAumentosDelEjercicio(Integer.parseInt(consoleUserSubMenuResponse.nextLine()));

        System.out.println("Ingrese SAC del ejercicio generado en 2022");
        sac.setAumentosDelEjercicio(Integer.parseInt(consoleUserSubMenuResponse.nextLine()));

        updateAndPrintBalance();
    }


    public void updateBalance(){
        rai.setReversoSaldo(rai.getSaldoReajustado());
        rai.setSaldoAntesDeDistribuciones(rai.getAumentosDelEjercicio());
        sac.setSaldoAntesDeDistribuciones(sac.getSaldoReajustado() + sac.getAumentosDelEjercicio());
    }

    public void printBalanceUpdated(){
        System.out.println("| RAI(01.01.2022): "+rai.getSaldoInicial()+" | SAC(01.01.2022): "+sac.getSaldoInicial()+" |");
        System.out.println("| RAI(Reajuste): "+rai.getReajuste()+" | SAC(Reajuste): "+sac.getReajuste()+" |");
        System.out.println("| RAI REVERSO: "+-rai.getReversoSaldo());
        System.out.println("| SAC del Año: "+"----------------"+sac.getAumentosDelEjercicio());
        System.out.println("| RAI(ant. de dist.: "+rai.getSaldoAntesDeDistribuciones()+" | SAC(ant. de dist.: "+sac.getSaldoAntesDeDistribuciones()+" |");
    }

    public void updateAndPrintBalance(){
        updateBalance();
        printBalanceUpdated();
    }

    public void case3(){
        System.out.println("Seleccionó ingresar distribuciones");
        dist.setDistribucionesMensuales();
        System.out.println("Se ingresaron: "+ dist.getDistribucionesTotales());

    }

    public void case5(){
        // LOGICA DE PROCESAR LOS RETIROS
        System.out.println("Seleccionó generar RRE");

        dist.procesarDistribuciones();

        System.out.println("DIST. REAJ: "+dist.sumaDistribucionesReajustadas());
        imputacionesAlSac();
        imputacionesAlRai();
    }

    public void imputacionesAlSac(){
        int alcanceSac = (int) Math.ceil(dist.sumaDistribucionesReajustadas() * 0.369863);
        sac.setImputacionesDelEjercicio(alcanceSac);

        if(sac.getImputacionesDelEjercicio() > sac.getSaldoAntesDeDistribuciones() ){
            sac.setImputacionesDelEjercicio(sac.getSaldoAntesDeDistribuciones());
        }

        sac.setSaldoFinal(sac.getSaldoAntesDeDistribuciones() - sac.getImputacionesDelEjercicio());

        System.out.println("IMPUTACIONES DEL EJ. : "+sac.getImputacionesDelEjercicio());
        System.out.println("SALDO FINAL DEL EJ.  : "+sac.getSaldoFinal());
    }

    public void imputacionesAlRai(){

        int saldoRaiPreCierre = rai.saldoAntesDeDistribuciones- dist.distribucionesTotalesReajustadas;

        if(saldoRaiPreCierre < 0 ){
            rai.distribuciones_no_imputadas = dist.distribucionesTotalesReajustadas-rai.saldoAntesDeDistribuciones;
            rai.imputacionesDelEjercicio=rai.saldoAntesDeDistribuciones;
            rai.saldoFinal = 0;
        }

        System.out.println("RAI IMPUTADO   :"+rai.imputacionesDelEjercicio);
        System.out.println("RAI SALDO FINAL: "+rai.saldoFinal);
        System.out.println("RAI NO IMPUT   :"+rai.distribuciones_no_imputadas);
    }

    //-------------------------------------------------------------------------------------------------------
    // DE ACÁ CONTINUAR REVISANDO   |---->
    //-------------------------------------------------------------------------------------------------------


    /*
    public void case4(){
        System.out.println("Seleccionó ingresar GRNG");
        // Función que deduce los GRNG
    }


    public void case5(){


        // situación del RAI



        //-----------------------------------
        // REPORTE FINAL MODULARIZADO
        //-----------------------------------
        imprimirTitulos();
        imprimirSaldosIniciales();
        imprimirReajustes();
        imprimirSaldoReajustado();
        imprimirReversos();
        imprimirRaiDelEjercicio();
        imprimirSacDelEjercicio();
        imprimirSaldoAntesDeDistribuciones();
        imprimirDistribuciones(sacImputado);
        imprimirSaldoRemanente();
        imprimirDistNoImputadas();

        System.out.println("*******");

        for(int j = 0; j <= 11; j++ ){
            System.out.println("Mes: "+(j+1)+": "+ dist.distribuciones[j]);
        }
    }

    public void caseDefault(){
        System.out.println("Ingresa una respuesta válida");
    }

    //     FUNCIONES DEL REPORTE
    public void imprimirTitulos(){
        System.out.println("DETALLE........................."+"-----RAI----"+"-----SAC----");
    }

    public void imprimirSaldosIniciales(){
        String dato1 = Funciones.rellenar(rai.getSaldoInicial());
        String dato2 = Funciones.rellenar(sac.getSaldoInicial());

        System.out.println("Saldos de apertura.............."+dato1+dato2);
    }

    public void imprimirReajustes(){
        String dato1 = Funciones.rellenar(rai.getReajuste());
        String dato2 = Funciones.rellenar(sac.getReajuste());
        System.out.println("Reajustes (13.3%)..............."+dato1+dato2);
    }

    public void imprimirSaldoReajustado(){
        String dato1 =Funciones.rellenar(rai.getSaldoReajustado());
        String dato2 =Funciones.rellenar(sac.getSaldoReajustado());
        System.out.println("Saldos reajustados.............."+dato1+dato2);
    }

    public void imprimirReversos(){
        String dato1 = Funciones.rellenar(-rai.getReversoSaldo());
        System.out.println("Reverso del RAI................."+dato1);
    }

    public void imprimirRaiDelEjercicio(){
        String dato1 = Funciones.rellenar(rai.getAumentosDelEjercicio());
        System.out.println("RAI del ejercicio..............."+dato1);
    }

    public void imprimirSacDelEjercicio(){
        String dato1 = "            ";
        String dato2 = Funciones.rellenar(sac.getAumentosDelEjercicio());
        System.out.println("SAC del ejercicio..............."+dato1+dato2);
    }

    public void imprimirSaldoAntesDeDistribuciones(){
        String dato1 = Funciones.rellenar(rai.getSaldoAntesDeDistribuciones());
        String dato2 = Funciones.rellenar(sac.getSaldoAntesDeDistribuciones());
        System.out.println("Saldos antes de distribuciones.."+dato1+dato2);
    }

    public void imprimirDistribuciones(int dato3){
        String dato1 = Funciones.rellenar(dist.getDistribucionesTotales());
        String dato2 = Funciones.rellenar(dato3);
        System.out.println("Distribuciones.................."+dato1+dato2);
    }

    public void imprimirSaldoRemanente(){
        String dato1 = Funciones.rellenar(rai.getSaldoFinal());
        String dato2 = Funciones.rellenar(sac.getSaldoFinal());
        System.out.println("Saldo Remanente................."+dato1+dato2);
    }

    public void imprimirDistNoImputadas(){
        String dato1 = Funciones.rellenar(rai.getDistribuciones_no_imputadas());
        System.out.println("Dist. no Imp. (Cod. 1193, F22).."+dato1);
    }
    */

}
