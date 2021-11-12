package LessonNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        //создаем адрес для канала
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 23456);
        //создаем с помощью сокет
        final SocketChannel socketChannel = SocketChannel.open();
        // подключаем сокет к каналу
        socketChannel.connect(socketAddress);

        try (Scanner scanner = new Scanner(System.in);
        ) {
// задаем размер буфера
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

            String msg;

            while (true) {
                System.out.println("Enter message for server...");
                msg = scanner.nextLine();

                if (msg.equals("end")) break;

// поучаем из строки массив байтов по выбранной кодировке
// массив байтов "оборачиваем" в буффер
// записываем буффер в сокет-канал
                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));

                Thread.sleep(2000);

// перемещаем последовательность байтов из канала в буффер, возвращаем количество прочитанных байт
                int bytesCount = socketChannel.read(inputBuffer);

                // Из буффера получаем массив байтров, из массива собираем строку
                // .trim() отрезаем пробелы по краям
                System.out.println(new String(inputBuffer.array(), 0, bytesCount, StandardCharsets.UTF_8).trim());
// чистим буффер
                inputBuffer.clear();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            socketChannel.close();
        }


    }
}
