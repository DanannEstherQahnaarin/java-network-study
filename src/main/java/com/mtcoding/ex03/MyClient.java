package com.mtcoding.ex03;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost",20000)) {
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            bw.write("Hello");
            bw.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
