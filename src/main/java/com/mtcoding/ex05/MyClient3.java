package com.mtcoding.ex05;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient3 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost",20000)){
            Scanner keyBuffer = new Scanner(System.in);
            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                String keyboardData = keyBuffer.nextLine();
                pw.println(keyboardData);

                String response = br.readLine();
                System.out.println(response);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
