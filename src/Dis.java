import java.util.Scanner;

public class Dis {
    Scanner consoleUserDistributionResponse = new Scanner(System.in);
    int [] distribuciones;
    int distribucionesTotales;

    public void inicializar(){
        distribuciones = new int[12];

        distribuciones[0] = 0;
        distribuciones[1] = 0;
        distribuciones[2] = 0;
        distribuciones[3] = 0;
        distribuciones[4] = 0;
        distribuciones[5] = 0;
        distribuciones[6] = 0;
        distribuciones[7] = 0;
        distribuciones[8] = 0;
        distribuciones[9] = 0;
        distribuciones[10] = 0;
        distribuciones[11] = 0;

        setDistribucionesTotales();
    }

    public void setDistribucionesMensuales(){
        inicializar();
        for( int monthNumberMinusOne = 0; monthNumberMinusOne <= 11; monthNumberMinusOne++){
            System.out.println("From Month: "+(monthNumberMinusOne+1));
            int amountDistribution = Integer.parseInt(consoleUserDistributionResponse.nextLine());
            System.out.println(amountDistribution);
            distribuciones[monthNumberMinusOne] = amountDistribution;
        }
        setDistribucionesTotales();
    }

    public void setDistribucionesTotales(){
        for( int monthNumberMinusOne = 0; monthNumberMinusOne <= 11; monthNumberMinusOne++){
            distribucionesTotales += distribuciones[monthNumberMinusOne];
        }
    }

    public int getDistribucionesTotales() {
        return distribucionesTotales;
    }

    public void getDistribucionesMensuales(){
        getDistribucionEnero();
        getDistribucionFebrero();
        getDistribucionMarzo();
        getDistribucionAbril();
        getDistribucionMayo();
        getDistribucionJunio();
        getDistribucionJunio();
        getDistribucionJulio();
        getDistribucionAgosto();
        getDistribucionSeptiembre();
        getDistribucionOctubre();
        getDistribucionNoviembre();
        getDistribucionDiciembre();
    }

    public void getDistribucionEnero(){
        System.out.println("Enero: "+distribuciones[0]);
    }

    public void getDistribucionFebrero(){
        System.out.println("Febrero: "+distribuciones[1]);
    }

    public void getDistribucionMarzo(){
        System.out.println("Marzo: "+distribuciones[2]);
    }

    public void getDistribucionAbril(){
        System.out.println("Abril: "+distribuciones[3]);
    }

    public void getDistribucionMayo(){
        System.out.println("Mayo: "+distribuciones[4]);
    }
    public void getDistribucionJunio(){
        System.out.println("Mayo: "+distribuciones[5]);
    }
    public void getDistribucionJulio(){
        System.out.println("Julio: "+distribuciones[6]);
    }
    public void getDistribucionAgosto(){
        System.out.println("Agosto: "+distribuciones[7]);
    }
    public void getDistribucionSeptiembre(){
        System.out.println("Septiembre: "+distribuciones[8]);
    }

    public void getDistribucionOctubre(){
        System.out.println("Octubre: "+distribuciones[9]);
    }

    public void getDistribucionNoviembre(){
        System.out.println("Noviembre: "+distribuciones[10]);
    }

    public void getDistribucionDiciembre(){
        System.out.println("Diciembre: "+distribuciones[11]);
    }





    //----------A REVISION ----->

    /*
    public int getDistribucionesTotales() {
        return distribucionesTotales;
    }
    public void procesarDistribuciones(){
        distribuciones[0] *= Constantes.DIR_CM_ENE;
        distribuciones[1] *= Constantes.DIR_CM_FEB;
        distribuciones[2] *= Constantes.DIR_CM_MAR;
        distribuciones[3] *= Constantes.DIR_CM_ABR;
        distribuciones[4] *= Constantes.DIR_CM_MAY;
        distribuciones[5] *= Constantes.DIR_CM_JUN;
        distribuciones[6] *= Constantes.DIR_CM_JUL;
        distribuciones[7] *= Constantes.DIR_CM_AGO;
        distribuciones[8] *= Constantes.DIR_CM_SEP;
        distribuciones[9] *= Constantes.DIR_CM_OCT;
        distribuciones[10] *= Constantes.DIR_CM_NOV;
        distribuciones[11] *= Constantes.DIR_CM_DIC;

    }

    public int sumaDistribuciones(){
        for(int j = 0; j <= 11; j++ ){
            distribucionesTotales += this.distribuciones[j];
            setDistribucionesTotales(distribucionesTotales);
        }
        return distribucionesTotales;
    }
    */
}

