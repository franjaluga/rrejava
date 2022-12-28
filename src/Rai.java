public class Rai extends Registro{

    int reversoSaldo;
    public void inicializar(){
        super.inicializar();
        this.reversoSaldo= 0;
    }

    public int getReversoSaldo() {
        return reversoSaldo;
    }

    public void setReversoSaldo(int reversoSaldo) {
        this.reversoSaldo = reversoSaldo;
    }

}
