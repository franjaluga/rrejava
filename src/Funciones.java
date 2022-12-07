public class Funciones {

    public static String rellenar(String dato){
        String newData = dato;
        int largo_dato = dato.length();

        String vacio = " ";
        int maximo = 9;

        for(int i = largo_dato; i <= maximo; i++){
            vacio = vacio + " ";
        }

        String cadena_final = vacio + newData;

        return cadena_final;
    }

}
