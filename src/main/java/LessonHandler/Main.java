package LessonHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(23456);
        ExecutorService executorService = Executors.newFixedThreadPool(50);

        // не занимаем сервер одним потоком клиента
    while (true) {
        Socket s = ss.accept();
        executorService.submit(new RequestHandler(s));
    }
    }
}
