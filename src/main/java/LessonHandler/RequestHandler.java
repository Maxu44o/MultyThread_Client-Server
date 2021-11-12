package LessonHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestHandler implements Runnable {

    private Socket client;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (InputStream in = client.getInputStream();
             OutputStream out = client.getOutputStream();) {
        //TODO
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
