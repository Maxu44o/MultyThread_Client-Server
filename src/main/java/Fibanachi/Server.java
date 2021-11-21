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


    private long calculate(int input) {
        long[] fibAray = new long[input + 1];
        fibAray[0] = 0;
        fibAray[1] = 1;
        for (int i = 2; i <= input; i++) {
            fibAray[i] = fibAray[i - 1] + fibAray[i - 2];
        }
        return fibAray[input];
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
                out.println("Значение получено, вычисления начались");
                int number = Integer.parseInt(input);
                Thread.sleep(CALCULATIONPERIOD);
                out.println("значение члена ряда Фибоначчи с номером: " + number + " равно: " + calculate(number));
            }
        } catch (IOException |
                InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Сервер остановлен");
        }

    }


}

