package pers.fangjing.netty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by fang_j on 2018/7/24.
 */
public class SingleThreadSocketServer {
    public static final int PORT_NUMBER = 9999;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        Socket clientSocket = serverSocket.accept();
        System.out.println(clientSocket.getInetAddress());
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String request, response;

        while ((request = in.readLine()) != null) {
            if ("done".equalsIgnoreCase(request)) {
                out.println("done");
                break;
            }

            response = System.currentTimeMillis() + "  " + request;
            out.println(response);
        }
    }
}
