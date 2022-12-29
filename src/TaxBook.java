import java.util.ArrayList;
import java.util.Scanner;

public class TaxBook{
    Rai rai = new Rai();
    Sac sac = new Sac();
    Dis dist =  new Dis();

    Scanner consoleUserSubMenuResponse = new Scanner(System.in);

    public void initializeBook(){
        rai.inicializar();
        sac.inicializar();
        dist.inicializar();
    }

    public void limpiarPantalla(){
        try {
            System.out.println("Presione Enter para continuar...");
            new java.util.Scanner(System.in).nextLine();

            String sistemaOperativo = System.getProperty("os.name");
            ArrayList<String> comando= new ArrayList<String>();
            if(sistemaOperativo.contains("Windows")){
                comando.add("cmd");
                comando.add("/C");
                comando.add("cls");
            }else{
                comando.add("clear"); //UNIX => MAC, LINUX
            }
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process iniciarProceso =pb.inheritIO().start();
            iniciarProceso.waitFor();
            System.out.println("Se han ingresado Datos");

        } catch (Exception e) {
            System.err.println("Error al limpiar la pantalla"+e.getMessage());
        }
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

        updateBalance();
        limpiarPantalla();
    }

    public void updateAndPrintBalance(){
        updateBalance();
        printBalanceUpdated();
    }

    public void updateBalance(){
        rai.setReversoSaldo(rai.getSaldoReajustado());
        rai.setSaldoAntesDeDistribuciones(rai.getAumentosDelEjercicio());
        sac.setSaldoAntesDeDistribuciones(sac.getSaldoReajustado() + sac.getAumentosDelEjercicio());
    }

    public void printBalanceUpdated(){
        System.out.println("|===================|=============|==============|");
        System.out.println("| Operaciones       |     RAI     |   SAC-CC-CR  |");
        System.out.println("|===================|=============|==============|");
        System.out.println("| RAI(01.01.2022)   |"+Funciones.rellenar(rai.getSaldoInicial()) +" | "+Funciones.rellenar(sac.getSaldoInicial())+" |");
        System.out.println("| RAI(Reajuste)     |"+Funciones.rellenar(rai.getReajuste())+" | "+Funciones.rellenar(sac.getReajuste())+" |");
        System.out.println("| RAI Reajustado    |"+Funciones.rellenar(rai.getSaldoReajustado())+" | "+Funciones.rellenar(sac.getSaldoReajustado())+" |");
        System.out.println("| Reverso RAI       |"+Funciones.rellenar(-rai.getReversoSaldo())+" | "+Funciones.rellenar(0)+" |");
        System.out.println("| SAC del Año       |"+Funciones.rellenar(0)+" | "+Funciones.rellenar(sac.getAumentosDelEjercicio())+" |");
        System.out.println("| RAI del Año       |"+Funciones.rellenar(rai.getAumentosDelEjercicio())+" | "+Funciones.rellenar(0)+" |");
        System.out.println("|===================|=============|==============|");
        System.out.println("| Sdo. ant. dist.   |"+Funciones.rellenar(rai.getSaldoAntesDeDistribuciones())+" | "+Funciones.rellenar(sac.getSaldoAntesDeDistribuciones())+" |");
        System.out.println("|===================|=============|==============|");
    }

    public void case2(){
        System.out.println("Seleccionó ingresar RAI y SAC al 31.12.2022");
        System.out.println("Ingrese RAI final");
        rai.setAumentosDelEjercicio(Integer.parseInt(consoleUserSubMenuResponse.nextLine()));

        System.out.println("Ingrese SAC del ejercicio generado en 2022");
        sac.setAumentosDelEjercicio(Integer.parseInt(consoleUserSubMenuResponse.nextLine()));

        updateBalance();
        limpiarPantalla();
    }


    public void case3(){
        System.out.println("Seleccionó ingresar distribuciones");
        dist.setDistribucionesMensuales();
        System.out.println("|================================================|");
        System.out.println("|  Se ingresaron distribuciones por : "+ dist.getDistribucionesTotales());
        System.out.println("|================================================|");
        limpiarPantalla();
    }

    public void case5(){

        System.out.println("Seleccionó generar RRE");

        dist.procesarDistribuciones();

        imputacionesAlSac();
        imputacionesAlRai();
        printReporte();
        limpiarPantalla();
    }

    public void imputacionesAlSac(){

        int alcanceSac = (int) Math.ceil(sac.getSaldoAntesDeDistribuciones() / 0.369863);

        if(sac.getImputacionesDelEjercicio() > alcanceSac ){
            sac.setImputacionesDelEjercicio(sac.getSaldoAntesDeDistribuciones());
        }else{
            sac.setImputacionesDelEjercicio((int) (dist.getDistribucionesTotales() * 0.369863));
        }
    }


    public int finalSacDistribution(){
        int finalSacDist = 0;
        int maxSacDist = sac.getSaldoAntesDeDistribuciones();
        int actualSacDist = sac.getImputacionesDelEjercicio();

        if(actualSacDist > maxSacDist){
            finalSacDist = maxSacDist;
        }else{
            finalSacDist = actualSacDist;
        }
        return finalSacDist;
    }

    public void imputacionesAlRai() {
        rai.setImputacionesDelEjercicio(dist.getDistribucionesTotalesReajustadas());

        int saldoRaiPreCierre = rai.getSaldoAntesDeDistribuciones() - dist.getDistribucionesTotalesReajustadas();
        rai.setDistribuciones_no_imputadas(Math.abs(saldoRaiPreCierre));

        if(saldoRaiPreCierre < 0 ){
            rai.saldoFinal = 0;
        }


    }

    public void printReporte(){
        updateBalance();
        printImputaciones();
    }

    public void printImputaciones(){

        int distTotalesReajustadas = dist.getDistribucionesTotales();
        int raiParaDistribuir = rai.getSaldoAntesDeDistribuciones();
        int sacParaDistribuir = sac.getSaldoAntesDeDistribuciones();

        int raiAux = 0;

        int raiCC = 0;
        int raiSC = 0;

        int raiNI = 0;

        int sacALC = (int) ( sacParaDistribuir / 0.369863 );

        int sacCC1 = 0;
        int sacCC2 = 0;

        // RESOLVER EL RAI EN 3 PARTES

        if(raiParaDistribuir >= distTotalesReajustadas){
            // el RAI ALCANZA
            raiAux = distTotalesReajustadas;
        }else{
            // el RAI NO alcanza
            raiAux = raiParaDistribuir;
            raiNI = distTotalesReajustadas - raiAux;
        }

        //AHORA, SE DEBE DIVIDIR ENTRE RAICC Y RAISC, ESO DEPENDE DEL sacALC
        if(sacALC > raiAux){
            // el SAC ALCANZA
            raiCC = raiAux;
        }else{
            // el SAC no alcanza
            raiCC = sacALC;
            raiSC = raiAux - sacALC;
        }

        // RESOLVER EL SAC EN 2 PARTES

        // como raiCC siempre está bien calculado, el sacCC1 VA DIRECTO
        sacCC1 = (int) ( Math.ceil(raiCC * 0.369863) );
        if(sacCC1 > sacParaDistribuir){
            sacCC1 = sacParaDistribuir;
        }

        // en cambio sacCC2, dependerá si ya no queda RAI.

        // imputacionesSAC = retiros * 0.369863
        // si las imputacionesSAC > sacCC1, entonces anotar el saldo
        // imputacionesSAC - sacCC2


        if( Math.ceil(distTotalesReajustadas * 0.369863) >  sacCC1 ){
            sacCC2 = (int) ( Math.ceil(distTotalesReajustadas * 0.369863) ) - sacCC1;

            if( (sacCC1 + sacCC2) > sacParaDistribuir){

                sacCC2 = sacParaDistribuir - sacCC1;
                if(sacCC2 < 0){
                    sacCC2 = 0;
                }
            }
        }

        int remanenteRAI;
        int remanenteSAC;

        remanenteRAI = raiParaDistribuir - raiCC - raiSC;
        remanenteSAC = sacParaDistribuir - sacCC1 - sacCC2;

        printBalanceUpdated();

        System.out.println("| Dist.             |                            |");
        System.out.println("|                   |"+Funciones.rellenar(raiCC)+" | "+Funciones.rellenar(sacCC1)+" |");
        System.out.println("|                   |"+Funciones.rellenar(raiSC)+" | "+Funciones.rellenar(sacCC2)+" |");
        System.out.println("|===================|=============|==============|");
        System.out.println("| Remanentes        |"+Funciones.rellenar( remanenteRAI )+" | "+Funciones.rellenar( remanenteSAC )+" |");
        System.out.println("|===================|=============|==============|");
        System.out.println("| Sdo. no imput.    |"+Funciones.rellenar(raiNI)+" | ");
        System.out.println("|===================|=============|");
            }
}
