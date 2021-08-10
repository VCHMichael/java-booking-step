package app;

import app.console.Console;
public class Main {
    public static void main(String[] args) {
        try {
            Console console = new Console();
            console.main(null);
        }
        catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e.getCause();
            System.out.println(cause);
        }
    }
}
