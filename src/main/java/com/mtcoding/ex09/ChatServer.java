package com.mtcoding.ex09;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ClientThread implements Runnable{
    Socket socket;
    PrintWriter sender;
    Scanner receiver;

    public ClientThread(Socket socket, PrintWriter sender, Scanner receiver) {
        this.socket = socket;
        this.sender = sender;
        this.receiver = receiver;
    }

    @Override
    public void run() {
        while (true){
            String msg = receiver.nextLine();

            for (ClientThread c : ChatServer.clientThreadList){
                if(c != this){
                    c.sender.println(msg);
                }
            }
        }
    }
}

public class ChatServer {
    static List<ClientThread> clientThreadList = new ArrayList<>();

    public static void main(String[] args) {

        try (ServerSocket ss = new ServerSocket(10000)){
            while (true){
                Socket socket = ss.accept();
                PrintWriter sender = new PrintWriter(socket.getOutputStream(),true);
                Scanner receiver = new Scanner(socket.getInputStream());

                ClientThread clientThread = new ClientThread(socket, sender,receiver);

                clientThreadList.add(clientThread);
                new Thread(clientThread).start();
            }

        }catch (IOException ignored){

        }
    }
}
