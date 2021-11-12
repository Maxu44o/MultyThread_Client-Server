package LessonIO;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 23456;

        new Thread(new Server(port)).start();
        new Thread(new Client(port)).start();
    }
}
