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
        updateAndPrintBalance();
        printImputaciones();
    }

    public void printImputaciones(){


        System.out.println("*******************");
        int distTotalesReajustadas = dist.getDistribucionesTotales();
        int raiParaDistribuir = rai.getSaldoAntesDeDistribuciones();
        int sacParaDistribuir = sac.getSaldoAntesDeDistribuciones();

        int raiAux = 0;
        int raiR1 = 0;
        int raiR2 = 0;
        int raiR3 = 0;

        int sacR1 = 0;
        int sacR2 = 0;


        System.out.println("distTotalesReajustadas = " + distTotalesReajustadas);
        System.out.println("¿Cuanto hay de RAI? " + raiParaDistribuir);
        System.out.println("¿Cuanto hay de SAC? " + sacParaDistribuir);


        // RESOLVER EL RAI EN 3 PARTES
        // RESOLVER EL SAC EN 2 PARTES


        System.out.println("I1: " + raiR1 + " | " + sacR1);
        System.out.println("I2: " + raiR2 + " | " + sacR2);
        System.out.println("I3: " + raiR3);

    }
}
