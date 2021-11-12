package Fibanachi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       final int PORT = 23456;
        final String IP = "localhost";
        new Thread(new Server(PORT)).start();
        new Thread(new Client(IP,PORT)).start();
    }
}
