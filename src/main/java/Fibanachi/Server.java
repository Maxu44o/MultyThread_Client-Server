package Fibanachi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    final long CALCULATIONPERIOD = 5000;
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
                String input = in.readLine();
                if (input.equals("end")) break;

                if (input != null) out.println("Значение получено, вычисления начались");
                Thread.sleep(CALCULATIONPERIOD);


                int number = Integer.parseInt(input);
                if (number != 0) {
                    int[] fibAray = new int[number + 1];
                    fibAray[0] = 0;
                    fibAray[1] = 1;
                    for (int i = 2; i <= number; i++) {
                        fibAray[i] = fibAray[i - 1] + fibAray[i - 2];
                    }
                    out.println("значение члена ряда Фибоначчи с номером: " + number + " равно: " + fibAray[number]);
                } else out.println("Введите зачение не равное нулю");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Сервер остановлен");
        }
    }
}
