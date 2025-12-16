package com.mtcoding.ex04;

import java.io.*;
import java.net.Socket;

public class MyClient2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("192.168.0.58",20000)){
            InputStream keybo = System.in;
            InputStreamReader kReader = new InputStreamReader(keybo);
            BufferedReader keyBuffer = new BufferedReader(kReader);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter ow = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(ow);

            while (true){
                String keyboardData = keyBuffer.readLine();
                bw.write(keyboardData);
                bw.write("\n");
                bw.flush();
            }

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
