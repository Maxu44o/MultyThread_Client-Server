package LessonIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    int port;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        try (ServerSocket ss = new ServerSocket(port);
             Socket socket = ss.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            while (true) {
                String line = in.readLine();
                out.println("Echo: " + line);

                if (line.equalsIgnoreCase("end")) break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Сервер остановлен");
        }
    }
}
