public class Distribuciones {
    int [] distribuciones;
    int distribucionesTotales;

    public void inicializar(){
        distribuciones = new int[12];

        for(int i = 0; i <=11; i++){
            distribuciones[i] = 0;
        }
        distribucionesTotales = 0;
    }

    public int getDistribucionesTotales() {
        return distribucionesTotales;
    }

    public void setDistribucionesTotales(int distribucionesTotales) {
        this.distribucionesTotales = distribucionesTotales;
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

    public void sumaDistribuciones(){
        for(int j = 0; j <= 11; j++ ){
            distribucionesTotales += this.distribuciones[j];
            setDistribucionesTotales(distribucionesTotales);
        }
    }
}
