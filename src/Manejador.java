import java.util.Scanner;

public class Manejador {
    Scanner sc_Registro = new Scanner(System.in);
    Rai rai = new Rai();
    Sac sac = new Sac();
    Distribuciones dist =  new Distribuciones();

    public void instanciarLibros(){
        // inicializa el Rai
        rai.inicializar();
        // Inicializa el Sac
        sac.inicializar();
        // Inicializa distribuciones
        dist.inicializar();
    }

    public void response_case1(){
        System.out.println("Seleccionó ingresar datos iniciales");
        System.out.println("Ingrese RAI de apertura (01.01.2022)");
        rai.setSaldoInicial(Integer.parseInt(sc_Registro.nextLine()));
        System.out.println("Ingrese SAC apertura (01.01.2022)");
        sac.setSaldoInicial(Integer.parseInt(sc_Registro.nextLine()));
        System.out.println("se ingresó RAI Inicial (01.01.2022)>>> "+rai.getSaldoInicial());
        System.out.println("se ingresó SAC inicial (01.01.2022)>>> "+sac.getSaldoInicial());
    }

    public void response_case2(){
        System.out.println("Seleccionó ingresar RAI y SAC al 31.12.2022");
        System.out.println("Ingrese RAI final");
        rai.setAumentosDelEjercicio(Integer.parseInt(sc_Registro.nextLine()));
        System.out.println("ingresó>>> "+rai.getAumentosDelEjercicio());
        System.out.println("Ingrese SAC del ejercicio generado en 2022");
        sac.setAumentosDelEjercicio(Integer.parseInt(sc_Registro.nextLine()));
        System.out.println("ingresó>>> "+sac.getAumentosDelEjercicio());
    }

    public void response_case3(){
        System.out.println("Seleccionó ingresar distribuciones");
        for( int i = 0; i <= 11; i++){
            System.out.println("Distribuciones (históricas) Mes: "+(i+1)+"/2022");
            dist.distribuciones[i] = Integer.parseInt(sc_Registro.nextLine());
            System.out.println("mes x "+ (i+1) + " "+dist.distribuciones[i]);
        }
    }

    public void response_case4(){
        System.out.println("Seleccionó ingresar GRNG");
        // Función que deduce los GRNG
    }


    public void response_case5(){
        // Función generadora de RRE
        System.out.println("Seleccionó generar RRE");

        // 1. Reajustes
        rai.setSaldoReajustado((int) Math.ceil(rai.saldoInicial * (1 + Constantes.CM_22)));
        sac.setSaldoReajustado((int) Math.ceil(sac.saldoInicial * (1 + Constantes.CM_22)));

        rai.setReajuste(rai.getSaldoReajustado() - rai.getSaldoInicial());
        sac.setReajuste(sac.getSaldoReajustado() - sac.getSaldoInicial());

        //2. Reverso del RAI
        rai.setReversoSaldo(rai.getSaldoReajustado());

        //3. RAI generado en el año
        // >>>> ok

        //4. SAC generado en el año
        // >>>> ok

        //5. Saldo antes de distribuciones
        rai.setSaldoAntesDeDistribuciones(rai.getSaldoReajustado());
        sac.setSaldoAntesDeDistribuciones(sac.getSaldoReajustado() + sac.getAumentosDelEjercicio());

        //6. Procesar distribuciones
        dist.procesarDistribuciones();

        // sumar las distribuciones con un for
        dist.sumaDistribucioines();


        int sacImputado = (int) Math.ceil(dist.getDistribucionesTotales() * 0.369863);

        if(sacImputado > sac.getSaldoAntesDeDistribuciones() ){
            sacImputado = sac.getSaldoAntesDeDistribuciones();
        }

        sac.setSaldoFinal(sac.getSaldoAntesDeDistribuciones() - sacImputado);

        // situación del RAI

        rai.setSaldoFinal(rai.getSaldoAntesDeDistribuciones()- dist.getDistribucionesTotales());

        if(rai.getSaldoFinal() < 0 ){
            rai.setDistribuciones_no_imputadas(Math.abs(rai.getSaldoFinal()));
            rai.setSaldoFinal(0);
            dist.setDistribucionesTotales(rai.getSaldoFinal());
        }


        // Reporte final
        System.out.println("DETALLE........................."+Funciones.rellenar("RAI")+Funciones.rellenar("SAC"));
        System.out.println("Saldos de apertura.............."+Funciones.rellenar(String.valueOf(rai.getSaldoInicial())+Funciones.rellenar(String.valueOf(sac.getSaldoInicial()))));
        System.out.println("Reajustes (13.3%)..............."+Funciones.rellenar(String.valueOf(rai.getReajuste())+Funciones.rellenar(String.valueOf(sac.getReajuste()))));
        System.out.println("Saldos reajustados.............."+Funciones.rellenar(String.valueOf(rai.getSaldoReajustado())+Funciones.rellenar(String.valueOf(sac.getSaldoReajustado()))));
        System.out.println("Reverso del RAI................."+Funciones.rellenar(String.valueOf(-rai.getReversoSaldo())));
        System.out.println("RAI del ejercicio..............."+Funciones.rellenar(String.valueOf(rai.getAumentosDelEjercicio())));
        System.out.println("SAC del ejercicio..............."+Funciones.rellenar(" ")+Funciones.rellenar(String.valueOf(sac.getAumentosDelEjercicio())));
        System.out.println("Saldos antes de distribuciones.."+Funciones.rellenar(String.valueOf(rai.getSaldoAntesDeDistribuciones()))+Funciones.rellenar(String.valueOf(sac.getSaldoAntesDeDistribuciones())));
        System.out.println("Distribuciones.................."+Funciones.rellenar(String.valueOf(dist.getDistribucionesTotales()))+Funciones.rellenar(String.valueOf(sacImputado)));
        System.out.println("Saldo Remanente................."+Funciones.rellenar(String.valueOf(rai.getSaldoFinal()))+Funciones.rellenar(String.valueOf(sac.getSaldoFinal())));
        System.out.println("Dist. no Imp. (Cod. 1193, F22).."+Funciones.rellenar(String.valueOf(rai.getDistribuciones_no_imputadas())));

        System.out.println("*******");

        for(int j = 0; j <= 11; j++ ){
            System.out.println("Mes: "+(j+1)+": "+ dist.distribuciones[j]);
        }
    }

    public void response_case_default(){
        System.out.println("Ingresa una respuesta válida");
    }
}
