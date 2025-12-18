package com.mtcoding.ex10;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class UrlConnectionReader {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=0&ie=utf8&query=%EB%82%A0%EC%94%A8&ackey=48gblj7q");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");

            Scanner sc = new Scanner(conn.getInputStream());
            String html = "";

            while (sc.hasNextLine()){
                String line = sc.nextLine();
                html= html + line;
            }

            System.out.println(html);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
