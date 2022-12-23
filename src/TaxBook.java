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

        System.out.println("SAC->IMPUTACIONES DEL EJ. : "+sac.getImputacionesDelEjercicio());
        System.out.println("SAC->SALDO FINAL DEL EJ.  : "+sac.getSaldoFinal());
    }

    public void imputacionesAlRai() {

        int saldoRaiPreCierre = rai.getSaldoAntesDeDistribuciones() - dist.getDistribucionesTotalesReajustadas();
        rai.setDistribuciones_no_imputadas(Math.abs(saldoRaiPreCierre));
        System.out.println("RAI->PRE-CIERRE: "+saldoRaiPreCierre);

        if(saldoRaiPreCierre < 0 ){
            rai.saldoFinal = 0;
        }

        System.out.println("RAI->RECALCULAR-CIERRE: "+ rai.saldoFinal);
        System.out.println("DISTR. NO REAJ.: "+dist.getDistribucionesTotales());
        System.out.println("DISTR. REAJ->  : "+dist.getDistribucionesTotalesReajustadas() );

        System.out.println(("=================="));

        System.out.println("RAI IMPUTADO   :"+rai.getImputacionesDelEjercicio());
        System.out.println("RAI SALDO FINAL: "+rai.getSaldoFinal());
        System.out.println("RAI NO IMPUT   :"+rai.getDistribuciones_no_imputadas());

    }
}
