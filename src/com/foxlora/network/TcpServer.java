package com.foxlora.network;

import javax.print.DocFlavor;
import javax.print.attribute.HashDocAttributeSet;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TcpServer {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(7777);
            System.out.println("server is running at 7777" );


            while (true){
                Socket socket =  serverSocket.accept();//阻塞
                System.out.println("connected from  " + socket.getRemoteSocketAddress());
                Thread t = new Handler(socket);
                t.start();
            }






        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class Handler extends Thread{
    Socket socket;
    public Handler(Socket sock){
        this.socket = sock;
    }

    @Override
    public void run() {
        try (InputStream input = this.socket.getInputStream()) {
            try (OutputStream output = this.socket.getOutputStream()) {
                handle(input, output);
            }
        } catch (Exception e) {
            try {
                this.socket.close();
            } catch (IOException ioe) {
            }
            System.out.println("client disconnected.");
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        writer.write("hello\n");
        writer.flush();
        for (;;) {
            String s = reader.readLine();
            if (s.equals("bye")) {
                writer.write("bye\n");
                writer.flush();
                break;
            }
            writer.write("ok: " + s + "\n");
            writer.flush();
        }
    }

}