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
        System.out.println("| Operaciones       |    RAI      |    SAC       |");
        System.out.println("|===================|=============|==============|");
        System.out.println("| RAI(01.01.2022)   |"+Funciones.rellenar(rai.getSaldoInicial()) +" | "+Funciones.rellenar(sac.getSaldoInicial())+" |");
        System.out.println("| RAI(Reajuste)     |"+Funciones.rellenar(rai.getReajuste())+" | "+Funciones.rellenar(sac.getReajuste())+" |");
        System.out.println("| RAI Reajustado    |"+Funciones.rellenar(rai.getSaldoReajustado())+" | "+Funciones.rellenar(sac.getSaldoReajustado())+" |");
        System.out.println("| Reverso RAI       |"+Funciones.rellenar(-rai.getReversoSaldo())+" | "+Funciones.rellenar(0)+" |");
        System.out.println("| SAC del Año       |"+Funciones.rellenar(0)+" | "+Funciones.rellenar(sac.getAumentosDelEjercicio())+" |");
        System.out.println("| RAI del Año       |"+Funciones.rellenar(rai.getAumentosDelEjercicio())+" | "+Funciones.rellenar(0)+" |");
        System.out.println("|===================|=============|==============|");
        System.out.println("| RAI ant. dist.    |"+Funciones.rellenar(rai.getSaldoAntesDeDistribuciones())+" | "+Funciones.rellenar(sac.getSaldoAntesDeDistribuciones())+" |");
        System.out.println("|===================|=============|==============|");
    }

    public void case2(){
        System.out.println("Seleccionó ingresar RAI y SAC al 31.12.2022");
        System.out.println("Ingrese RAI final");
        rai.setAumentosDelEjercicio(Integer.parseInt(consoleUserSubMenuResponse.nextLine()));

        System.out.println("Ingrese SAC del ejercicio generado en 2022");
        sac.setAumentosDelEjercicio(Integer.parseInt(consoleUserSubMenuResponse.nextLine()));

        updateAndPrintBalance();
    }


    public void case3(){
        System.out.println("Seleccionó ingresar distribuciones");
        dist.setDistribucionesMensuales();
        System.out.println("|================================================|");
        System.out.println("|  Se ingresaron distribuciones por : "+ dist.getDistribucionesTotales());
        System.out.println("|================================================|");
    }

    public void case5(){

        System.out.println("Seleccionó generar RRE");

        dist.procesarDistribuciones();

        imputacionesAlSac();
        imputacionesAlRai();
        printReporte();
    }

    public void imputacionesAlSac(){
        int alcanceSac = (int) Math.ceil(dist.sumaDistribucionesReajustadas() * 0.369863);
        sac.setImputacionesDelEjercicio(alcanceSac);

        if(sac.getImputacionesDelEjercicio() > sac.getSaldoAntesDeDistribuciones() ){
            sac.setImputacionesDelEjercicio(sac.getSaldoAntesDeDistribuciones());
        }

        sac.setSaldoFinal(sac.getSaldoAntesDeDistribuciones() - sac.getImputacionesDelEjercicio());
    }

    public void imputacionesAlRai() {

        int saldoRaiPreCierre = rai.getSaldoAntesDeDistribuciones() - dist.getDistribucionesTotalesReajustadas();
        rai.setDistribuciones_no_imputadas(Math.abs(saldoRaiPreCierre));

        if(saldoRaiPreCierre < 0 ){
            rai.saldoFinal = 0;
        }


    }

    public void printReporte(){
        updateAndPrintBalance();
        printImputaciones();
    }

    public void printImputaciones(){

        if(DistributionIsBiggerThanBalance()){
            System.out.println(DistributionIsBiggerThanBalance());
            System.out.println("| RAI IMPUTADO      |"+Funciones.rellenar(rai.getSaldoAntesDeDistribuciones())+" | "+Funciones.rellenar(sac.getImputacionesDelEjercicio())+" |");
        }else{
            System.out.println(DistributionIsBiggerThanBalance());
            System.out.println("| RAI IMPUTADO      |"+Funciones.rellenar(dist.getDistribucionesTotalesReajustadas())+" | "+Funciones.rellenar(sac.getImputacionesDelEjercicio())+" |");
        }

        System.out.println("|===================|=============|==============|");
        System.out.println("| SALDO             |"+Funciones.rellenar(rai.getSaldoFinal())+" | "+Funciones.rellenar(sac.getSaldoFinal())+" |");
        System.out.println("|===================|=============|==============|");
        //System.out.println("|................................................|");
        //System.out.println("| RAI NO IMPUT      |"+Funciones.rellenar(rai.getDistribuciones_no_imputadas())+" | ");
        //System.out.println("|................................................|");
    }

    public boolean DistributionIsBiggerThanBalance(){
        if(dist.getDistribucionesTotalesReajustadas() > rai.getSaldoAntesDeDistribuciones()){
            return true;
        }else{
            return false;
        }
    }

}
