package LessonIO;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {
    int port;

    public Client(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (Socket client = new Socket("localhost", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)
        ) {
            String message;

            while (true) {
                System.out.println("Print message for server");
                message = scanner.nextLine();
                out.println(message);
                if (message.equals("end")) break;

                System.out.println("Server: " + in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент остановлен");
        }
    }
}
