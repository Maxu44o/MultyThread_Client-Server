package SpacesTerm;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 34567);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(inetSocketAddress);

        while (true) {
            try (SocketChannel socketChannel = serverSocketChannel.accept()) {
                final ByteBuffer buffer = ByteBuffer.allocate(2 << 10);

                while (socketChannel.isConnected()) {
                    int bytecount = socketChannel.read(buffer);
                    if (bytecount == -1) break;

                    String input = new String(buffer.array(), 0, bytecount, StandardCharsets.UTF_8);

                    System.out.println(buffer.array());


                    String output = input.replaceAll("\\s+", "");
                    System.out.println(output);
                    socketChannel.write(ByteBuffer.wrap((output).getBytes(StandardCharsets.UTF_8), 0, output.length()));
                    buffer.clear();
                }
            }
        }


    }
}
