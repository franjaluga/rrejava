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
        dist.sumaDistribuciones();


        int sacImputado = (int) Math.ceil(dist.getDistribucionesTotales() * 0.369863);

        if(sacImputado > sac.getSaldoAntesDeDistribuciones() ){
            sacImputado = sac.getSaldoAntesDeDistribuciones();
        }

        sac.setSaldoFinal(sac.getSaldoAntesDeDistribuciones() - sacImputado);

        // situación del RAI

        rai.setSaldoFinal(rai.getSaldoAntesDeDistribuciones()- dist.getDistribucionesTotales());

        if(rai.getSaldoFinal() < 0 ){
            rai.setDistribuciones_no_imputadas(Math.abs(dist.getDistribucionesTotales()-rai.getSaldoAntesDeDistribuciones()));
            rai.setSaldoFinal(0);
            dist.setDistribucionesTotales(rai.getSaldoAntesDeDistribuciones());
        }

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

    public void response_case_default(){
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

}
