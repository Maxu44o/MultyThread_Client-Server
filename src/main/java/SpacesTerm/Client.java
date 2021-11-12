package SpacesTerm;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress("localhost", 34567);

        final SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(socketAddress);

        try (Scanner scanner = new Scanner(System.in)) {
            final ByteBuffer buffer = ByteBuffer.allocate(2 << 10);
            while (true) {
                System.out.println("Введите строку");
                String input = scanner.nextLine();
                if (input.equals("end")) break;

                socketChannel.write(ByteBuffer.wrap(input.getBytes(StandardCharsets.UTF_8)));

                int bytecount = socketChannel.read(buffer);
                System.out.println(new String(buffer.array(), 0, bytecount, StandardCharsets.UTF_8).trim());
                buffer.clear();
            }
        } finally {
            socketChannel.close();
        }
    }
}
