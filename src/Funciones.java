public class Funciones {

    public static String rellenar(int dato){
        String newData = String.valueOf(dato);
        int largo_dato = newData.length();

        String vacio = " ";
        int maximo = 10;

        for(int i = largo_dato; i <= maximo; i++){
            vacio = vacio + " ";
        }

        String cadena_final = vacio + newData;

        return cadena_final;
    }


}
