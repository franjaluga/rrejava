import java.util.Scanner;

public class Registro {
        int raiInicial = 0;
        int sacInicial = 0;
        int reajusteRai = 0;
        int reajusteSac = 0;
        int raiInicialReajustado = 0;
        int sacInicialReajustado = 0;
        int raiReversado = 0;
        int raiEjercicio = 0;
        int sacEjercicio = 0;
        int raiAntesDeDistribuciones = 0;
        int sacAntesDeDistribuciones = 0;
        String mes = "";
        int [] distribuciones = new int[12];
        int distribucionesTotales = 0;
        int sacCastigado = 0;
        int raiRemanente = 0;
        int sacRemanente;
        double reajusteRre;
        int distribuciones_no_imputadas;
        Scanner sc_Registro = new Scanner(System.in);

        public void inicializar(){
            raiInicial = 0;
            sacInicial = 0;
            reajusteRai = 0;
            reajusteSac = 0;
            raiInicialReajustado = 0;
            sacInicialReajustado = 0;
            raiReversado = 0;
            raiEjercicio = 0;
            sacEjercicio = 0;
            raiAntesDeDistribuciones = 0;
            sacAntesDeDistribuciones = 0;
            mes = "";

            for(int i = 0; i <=11; i++){
                distribuciones[i] = 0;
            }
            distribucionesTotales = 0;
            sacCastigado = 0;
            raiRemanente = 0;
            sacRemanente = 0;
            reajusteRre = 0.133;
            distribuciones_no_imputadas = 0;
        }

        public void setRaiInicial(int raiInicial) {
            this.raiInicial = raiInicial;
        }

        public void setSacInicial(int sacInicial) {
            this.sacInicial = sacInicial;
        }

        public void response_case1(){
            System.out.println("Seleccionó ingresar datos iniciales");
            System.out.println("Ingrese RAI de apertura (01.01.2022)");
            this.setRaiInicial(Integer.parseInt(sc_Registro.nextLine()));
            System.out.println("Ingrese SAC apertura (01.01.2022)");
            this.sacInicial = Integer.parseInt(sc_Registro.nextLine());
            System.out.println("se ingresó RAI Inicial (01.01.2022)>>> "+this.raiInicial);
            System.out.println("se ingresó SAC inicial (01.01.2022)>>> "+this.sacInicial);
        }

        public void response_case2(){
            System.out.println("Seleccionó ingresar RAI y SAC al 31.12.2022");
            System.out.println("Ingrese RAI final");
            this.raiEjercicio = Integer.parseInt(sc_Registro.nextLine());
            System.out.println("ingresó>>> "+this.raiEjercicio);
            System.out.println("Ingrese SAC del ejercicio generado en 2022");
            this.setSacInicial(Integer.parseInt(sc_Registro.nextLine()));
            System.out.println("ingresó>>> "+this.sacEjercicio);
        }

        public void response_case3(){
            System.out.println("Seleccionó ingresar distribuciones");
            for( int i = 0; i <= 11; i++){
                System.out.println("Distribuciones (históricas) Mes: "+(i+1)+"/2022");
                this.distribuciones[i] = Integer.parseInt(sc_Registro.nextLine());
                System.out.println(this.mes+" "+ (i+1) + " "+this.distribuciones[i]);
            }
        }

        public void response_case4(){
            System.out.println("Seleccionó ingresar GRNG");
            // Función que deduce los GRNG
        }

        public void response_case5(){
            System.out.println("Seleccionó generar RRE");
            // Función generadora de RRE

            // 1. Reajustes
            this.raiInicialReajustado = (int) Math.ceil(this.raiInicial * (1 + this.reajusteRre));
            this.sacInicialReajustado = (int) Math.ceil(this.sacInicial * (1 + this.reajusteRre));

            this.reajusteRai = this.raiInicialReajustado - this.raiInicial;
            this.reajusteSac = this.sacInicialReajustado - this.sacInicial;

            //2. Reverso del RAI
            this.raiReversado = this.raiInicialReajustado;

            //3. RAI generado en el año
            // >>>> ok

            //4. SAC generado en el año
            // >>>> ok

            //5. Saldo antes de distribuciones
            this.raiAntesDeDistribuciones = this.raiEjercicio;
            this.sacAntesDeDistribuciones =  this.sacInicialReajustado + this.sacEjercicio;

            //6. Procesar distribuciones
            procesarDistribuciones();

            // sumar las distribuciones con un for
            for(int j = 0; j <= 11; j++ ){
                int dist = this.distribuciones[j];
                this.distribucionesTotales += dist;
            }


            int sacImputado = (int) Math.ceil(this.distribucionesTotales * 0.369863);

            if(sacImputado > this.sacAntesDeDistribuciones){
                sacImputado = this.sacAntesDeDistribuciones;
            }

            this.sacRemanente = this.sacAntesDeDistribuciones - sacImputado;

            // situación del RAI



            this.raiRemanente = this.raiAntesDeDistribuciones - this.distribucionesTotales;

            if(this.raiRemanente < 0 ){
                this.distribuciones_no_imputadas = Math.abs(this.raiRemanente);
                this.raiRemanente = 0;
                this.distribucionesTotales = this.raiRemanente;
            }


             // Reporte final
             System.out.println("DETALLE.............................."+Funciones.rellenar("RAI")+Funciones.rellenar("SAC"));
             System.out.println("Saldos de apertura..................."+Funciones.rellenar(String.valueOf(this.raiInicial))+Funciones.rellenar(String.valueOf(this.sacInicial)));
             System.out.println("Reajustes (13.3%)...................."+Funciones.rellenar(String.valueOf(this.reajusteRai))+Funciones.rellenar(String.valueOf(this.reajusteSac)));
             System.out.println("Saldos reajustados..................."+Funciones.rellenar(String.valueOf(this.raiInicialReajustado))+Funciones.rellenar(String.valueOf(this.raiInicialReajustado)));
             System.out.println("Reverso del RAI......................"+Funciones.rellenar(String.valueOf(-this.raiReversado)));
             System.out.println("RAI del ejercicio...................."+Funciones.rellenar(String.valueOf(this.raiEjercicio)));
             System.out.println("SAC del ejercicio...................."+Funciones.rellenar(" ")+Funciones.rellenar(String.valueOf(this.sacEjercicio)));
             System.out.println("Saldos antes de distribuciones......."+Funciones.rellenar(String.valueOf(this.raiAntesDeDistribuciones))+Funciones.rellenar(String.valueOf(this.sacAntesDeDistribuciones)));
             System.out.println("Distribuciones......................."+Funciones.rellenar(String.valueOf(this.distribucionesTotales))+Funciones.rellenar(String.valueOf(sacImputado)));
             System.out.println("Saldo Remanente......................"+Funciones.rellenar(String.valueOf(this.raiRemanente))+Funciones.rellenar(String.valueOf(this.sacRemanente)));
             System.out.println("Dist. no Imp. (Cod. 1193, F22)......."+Funciones.rellenar(String.valueOf(this.distribuciones_no_imputadas)));

             System.out.println("*******");

             for(int j = 0; j <= 11; j++ ){
             System.out.println("Mes: "+(j+1)+": "+ this.distribuciones[j]);
             }
        }

        public void response_case_default(){
            System.out.println("Ingresa una respuesta válida");
        }


        public void procesarDistribuciones(){
            distribuciones[0] *= Constantes.CM_ENE;
            distribuciones[1] *= Constantes.CM_FEB;
            distribuciones[2] *= Constantes.CM_MAR;
            distribuciones[3] *= Constantes.CM_ABR;
            distribuciones[4] *= Constantes.CM_MAY;
            distribuciones[5] *= Constantes.CM_JUN;
            distribuciones[6] *= Constantes.CM_JUL;
            distribuciones[7] *= Constantes.CM_AGO;
            distribuciones[8] *= Constantes.CM_SEP;
            distribuciones[9] *= Constantes.CM_OCT;
            distribuciones[10] *= Constantes.CM_NOV;
            distribuciones[11] *= Constantes.CM_DIC;
        }
}
