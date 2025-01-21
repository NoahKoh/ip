import java.util.Scanner;

public class Luna {

    Scanner sc = new Scanner(System.in);

    public void greet() {
        System.out.println("Hello! I'm " + this.getClass().getSimpleName() + "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void echo() {
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                exit();
                break;
            } else {
                System.out.println(command); // Just print the command itself
            }
        }
    }

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Luna chatBot = new Luna();
        chatBot.greet();
        chatBot.echo();
    }
}
