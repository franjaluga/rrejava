public class Registro {
    int saldoInicial;
    int reajuste;
    int saldoReajustado;
    int aumentosDelEjercicio;
    int saldoAntesDeDistribuciones;
    int imputacionesDelEjercicio;
    int saldoFinal;
    int distribuciones_no_imputadas;


    public void inicializar() {
        saldoInicial = 0;
        reajuste = 0;
        saldoReajustado = 0;
        aumentosDelEjercicio = 0;
        saldoAntesDeDistribuciones = 0;
        imputacionesDelEjercicio = 0;
        saldoFinal = 0;
        distribuciones_no_imputadas = 0;
    }


    // METODOS REVISADOS
    public void setSaldoInicial(int saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
    public int getSaldoInicial() {
        return saldoInicial;
    }

    public void setAumentosDelEjercicio(int aumentosDelEjercicio) {
        this.aumentosDelEjercicio = aumentosDelEjercicio;
    }
    public int getAumentosDelEjercicio() {
        return aumentosDelEjercicio;
    }

    public void setReajuste(int reajuste) {
        this.reajuste = reajuste;
    }
    public int getReajuste() {
        return reajuste;
    }

    public void setSaldoReajustado(int saldoReajustado) {
        this.saldoReajustado = saldoReajustado;
    }
    public int getSaldoReajustado() {
        return saldoReajustado;
    }





    //-------------------------------------------------------------------------------------------------------------
    //  GETTERS Y SETTERS
    //-------------------------------------------------------------------------------------------------------------








    public int getSaldoAntesDeDistribuciones() {
        return saldoAntesDeDistribuciones;
    }

    public void setSaldoAntesDeDistribuciones(int saldoAntesDeDistribuciones) {
        this.saldoAntesDeDistribuciones = saldoAntesDeDistribuciones;
    }

    public int getImputacionesDelEjercicio() {
        return imputacionesDelEjercicio;
    }

    public void setImputacionesDelEjercicio(int imputacionesDelEjercicio) {
        this.imputacionesDelEjercicio = imputacionesDelEjercicio;
    }

    public int getSaldoFinal() {
        return saldoFinal;
    }

    public void setSaldoFinal(int saldoFinal) {
        this.saldoFinal = saldoFinal;
    }

    public int getDistribuciones_no_imputadas() {
        return distribuciones_no_imputadas;
    }

    public void setDistribuciones_no_imputadas(int distribuciones_no_imputadas) {
        this.distribuciones_no_imputadas = distribuciones_no_imputadas;
    }
}