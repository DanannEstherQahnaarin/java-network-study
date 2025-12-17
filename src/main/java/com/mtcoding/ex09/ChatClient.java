package com.mtcoding.ex09;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class ReceiverThread implements Runnable{
    Scanner scanner;

    public ReceiverThread(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void run() {
        while (true){
            String msg = scanner.nextLine();
            System.out.println(msg);
        }
    }
}

class SenderThread implements Runnable{
    PrintWriter sender;
    Scanner keyboard;

    public SenderThread(PrintWriter sender, Scanner keyboard) {
        this.sender = sender;
        this.keyboard = keyboard;
    }

    @Override
    public void run() {
        while (true){
            String msg = keyboard.nextLine();
            sender.println(msg);
        }
    }
}

public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket= new Socket("192.168.0.99",10000)){
            while (true){
                PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
                Scanner receiver = new Scanner(socket.getInputStream());
                Scanner keyboard = new Scanner(System.in);

                new Thread(new ReceiverThread(receiver)).start();
                new Thread(new SenderThread(sender,keyboard)).start();
            }

        }catch (IOException e ){

        }
    }
}
