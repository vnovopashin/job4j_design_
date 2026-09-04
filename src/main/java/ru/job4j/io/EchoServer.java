package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9001)) {
            while (!server.isClosed()) {
                try (Socket socket = server.accept();
                     OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = input.readLine()) != null && !line.isEmpty()) {
                        sb.append(line);
                    }

                    String response;
                    if (sb.toString().contains("msg=Exit")) {
                        response = "Bye-Bye!\n";
                        server.close();
                    } else if (sb.toString().contains("msg=Hello")) {
                        response = "Hello\n";
                    } else {
                        response = "What\n";
                    }

                    output.write(("HTTP/1.1 200 OK\r\n"
                            + "Content-Length: " + response.length() + "\r\n"
                            + "\r\n" + response).getBytes(StandardCharsets.UTF_8));
                    output.flush();
                }
            }
        }
    }
}
