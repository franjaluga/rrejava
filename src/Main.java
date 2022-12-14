import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        PrintText.titles();
        TaxBook currentBook = new TaxBook();
        currentBook.createBook();

        Scanner scanConsoleUserResponse = new Scanner(System.in);
        TaxBook runResponse = new TaxBook();
        int consoleUserMenuResponse;

        do {
            PrintText.menu();
            consoleUserMenuResponse = Integer.parseInt(scanConsoleUserResponse.nextLine());
            switch (consoleUserMenuResponse) {
                case 0:
                    PrintText.exit();
                    break;
                case 1:
                    runResponse.case1();
                    break;
                case 2:
                    runResponse.case2();
                    break;
                case 3:
                    //runResponse.case3();
                    break;
                case 4:
                    //runResponse.case4();
                    break;
                case 5:
                    //runResponse.case5();
                    break;
                default:
                    //runResponse.caseDefault();
                    break;
            }
        }while (consoleUserMenuResponse != 0);
    }
}