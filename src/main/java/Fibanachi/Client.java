package Fibanachi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client implements Runnable {
    int port;
    String ip;

    public Client(String ip, int port) {
        this.port = port;
        this.ip = ip;
    }

    public void run() {
        try (Socket socket = new Socket(ip, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)
        ) {
            while (true) {
                System.out.println("Введите номер члена ряда Фибоначчи");
                String number = scanner.nextLine();
                out.println(number);
                if (number.equals("end")) break;
                System.out.println(in.readLine());
                System.out.println("Результат расчета " + in.readLine()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Клиент остановлен");
        }
    }
}
