import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("===Comprobación RRE 14-A - Régimen General Semi-Integrado===");
        System.out.println("Se usará TCV = 0.369863");
        System.out.println("Se usará CM = 13.3% AT2023");


        System.out.println("Ingrese RAI de apertura (01.01.2022)");
        int rai = Integer.valueOf(sc.nextLine());
        System.out.flush();
        System.out.println("ingresó "+rai);
        System.out.println("PASO 1 (OK)=============================================");

        System.out.println("Ingrese SAC apertura (01.01.2022");
        int sac = Integer.valueOf(sc.nextLine());
        System.out.println("ingresó "+sac);
        System.out.println("PASO 2 (OK)=============================================");

        System.out.println("Ingrese distribuciones DIC-2022");
        int distribuciones = Integer.valueOf(sc.nextLine());
        System.out.println("ingresó "+distribuciones);
        System.out.println("PASO 3 (OK)=============================================");


        //-----------------------------------
        // Reajustes
        //-----------------------------------
        double reajuste = 0.133;

        int raiReajustado;
        int sacReajustado;
        int cmRai;
        int cmSac;

        raiReajustado = (int) Math.ceil(rai * (1 + reajuste));
        sacReajustado = (int) Math.ceil(sac * (1 + reajuste));

        cmRai = raiReajustado - rai;
        cmSac = sacReajustado - sac;


        //-----------------------------------
        // Imputación de retiros al SAC
        //-----------------------------------

        int sacImputado = (int) Math.ceil(distribuciones * 0.369863);

        if(sacImputado > sac){
            sacImputado = sac;
        }

        int sacRemanente = sac - sacImputado;

        //-----------------------------------
        // Imputación de retiros al RAI
        //-----------------------------------
        int raiRemanente;
        int distribuciones_no_imputadas = 0;

        raiRemanente = rai - distribuciones;

        if(raiRemanente < 0 ){
            distribuciones_no_imputadas = Math.abs(raiRemanente);
            raiRemanente = 0;
            distribuciones = rai;
        }

        System.out.println("DETALLE.............................."+Relleno.rellenar("RAI")+Relleno.rellenar("SAC"));
        System.out.println("Saldos de apertura..................."+Relleno.rellenar(String.valueOf(rai))+Relleno.rellenar(String.valueOf(sac)));
        System.out.println("Reajustes (13.3%)...................."+Relleno.rellenar(String.valueOf(cmRai))+Relleno.rellenar(String.valueOf(cmSac)));
        System.out.println("Saldos reajustados..................."+Relleno.rellenar(String.valueOf(raiReajustado))+Relleno.rellenar(String.valueOf(sacReajustado)));
        System.out.println("Distribuciones......................."+Relleno.rellenar(String.valueOf(distribuciones))+Relleno.rellenar(String.valueOf(sacImputado)));
        System.out.println("Saldo Remanente......................"+Relleno.rellenar(String.valueOf(raiRemanente))+Relleno.rellenar(String.valueOf(sacRemanente)));
        System.out.println("Dist. no Imp. (Cod. 1193, F22)......."+Relleno.rellenar(String.valueOf(distribuciones_no_imputadas)));
    }
}