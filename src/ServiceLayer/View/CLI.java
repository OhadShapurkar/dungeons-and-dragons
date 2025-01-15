package ServiceLayer.View;

import java.util.Scanner;

public class CLI extends View {
    private Scanner scanner;

    public CLI() {
        this.scanner = new Scanner(System.in);
    }
    public void display(String message) {
        System.out.println(message);
    }

    @Override
    public String getInput() {
        return scanner.nextLine();
    }
    
}
