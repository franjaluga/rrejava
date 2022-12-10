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
        String [] distribuciones = new String[12];
        int distribucionesTotales = 0;
        int sacCastigado = 0;
        int raiRemanente = 0;
        int sacRemanente;
        double reajusteRre;
        int distribuciones_no_imputadas;

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
            distribuciones = new String[12];
            distribucionesTotales = 0;
            sacCastigado = 0;
            raiRemanente = 0;
            sacRemanente = 0;
            reajusteRre = 0.133;
            distribuciones_no_imputadas = 0;
        }
}
