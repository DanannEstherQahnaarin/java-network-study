package com.mtcoding.ex06;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class MyClient4 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost",20000)){

            PrintWriter pw = new PrintWriter(socket.getOutputStream(),true);

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Person p1 = new Person(1, "홍길동", 20, Arrays.asList("축구","농구"));
            Gson gson = new Gson();

            String json = gson.toJson(p1);

            pw.println(json);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
