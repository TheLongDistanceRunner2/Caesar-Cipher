import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws IOException {
        int n = 0; // shift
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter translation number: ");
        n = sc.nextInt();

        // input file name:
        String file_name = "input.txt";
        CaesarCipher cipher1 = new CaesarCipher(file_name, n);
        cipher1.setOutputFileName("output.txt");

        menu();
        int option = -1;
        option = sc.nextInt();

        if(option == 1) {
            cipher1.encode();
            cipher1.showOutputs();
            cipher1.writeToFile();
        }
        else if(option == 2) {
            cipher1.decode();
            cipher1.showOutputs();
            cipher1.writeToFile();
        }
        else {
            System.out.println("Wrong character!");
        }
    }

    private static void menu() {
        System.out.println("Choose what to do: ");
        System.out.println("1 - encode");
        System.out.println("2 - decode");
    }
}
