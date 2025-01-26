package luna;

import java.util.Scanner;

public class Ui {
    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet(String name) {
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
