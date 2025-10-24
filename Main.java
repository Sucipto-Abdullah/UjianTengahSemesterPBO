import controller.Control;
import data.data;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Load Data");
        data.loadData();
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

        while (Control.run) {
            Scanner command = new Scanner(System.in);
            String input = command.nextLine();
            Control.runCommand(input);
        }
        data.saveData();
    }
}