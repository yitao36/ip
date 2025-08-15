import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Tsundere {
    public static final int WIDTH = 40;
    public static final String HORIZONTAL_LINE = "_".repeat(WIDTH);

    public static void init() {
        System.out.println(HORIZONTAL_LINE);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you want...? I'm obviously busy!");

        System.out.println(HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);

        String exitMessage = "Hmph, I won't miss you!";
        System.out.println(exitMessage);

        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        init();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(br);

        outer:
        while (true) {
            String line = sc.nextLine();
            String[] words = line.split(" ");

            String command = words.length > 0 ? words[0] : "noInput";

            switch (line) {
                case "bye":
                    break outer;
                default:
                    System.out.println(line);
                    continue;
            }
        }

        exit();
    }
}
