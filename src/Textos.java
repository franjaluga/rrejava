public class Textos {
    public static String intro(){
        limpiar();
        System.out.println("|======================================================================|");
        System.out.println("| Comprobación RRE 14-A - Régimen General Semi-Integrado (versión alfa)|");
        System.out.println("|======================================================================|");
        System.out.println("| Se usará TCV = 0.369863                                              |");
        System.out.println("| Se usará CM  = 13.3% (AT2023)                                        |");
        System.out.println("|======================================================================|");

        return null;
    }

    public static String menu(){
        System.out.println("¿Que desea hacer?");
        System.out.println("1. Ingresar saldos iniciales 01.01.2022");
        System.out.println("2. Ingresar RAI y SAC Propio generado en el año");
        System.out.println("3. Ingresar distribuciones año 2022");
        System.out.println("4. Ingresar gastos rechazados no gravados");
        System.out.println("5. Generar Registro de rentas empresariales 14-A al 31.12.2022");
        System.out.println("0. Salir");
        return null;
    }

    public static String limpiar(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        return null;
    }

    public static String salir(){
        System.out.println("Ha salido de la aplicación");
        return null;
    }
}
