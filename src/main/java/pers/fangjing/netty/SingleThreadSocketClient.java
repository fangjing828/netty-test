package pers.fangjing.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by fang_j on 2018/7/24.
 */
public class SingleThreadSocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", SingleThreadSocketServer.PORT_NUMBER);

        final BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        final PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        Thread t = new Thread(new Runnable() {
            public void run() {
                String response;
                try {
                    while ((response = in.readLine()) != null) {
                        if ("done".equalsIgnoreCase(response)) {
                            break;
                        }
                        System.out.println(response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.setDaemon(true);
        t.start();
        String request = "";
        while (!request.equalsIgnoreCase("exit")) {
            Scanner scanner = new Scanner(System.in);
            request = scanner.nextLine();
            out.println(request);
        }
    }
}
