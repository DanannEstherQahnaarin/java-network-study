package com.mtcoding.ex10;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TempApp {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=0bd6eafcaec213c796db7445926cc744555fe4e8d27f7a425b8cccd0e92877fc&pageNo=1&numOfRows=1000&dataType=JSON&base_date=20251218&base_time=1200&nx=98&ny=75#");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");

            Scanner sc = new Scanner(conn.getInputStream());
            String json = "";

            while (sc.hasNextLine()){
                String line = sc.nextLine();
                json= json + line;
            }

            Hello hello = new Gson().fromJson(json, Hello.class);
            Hello.Response.Body.Items.Item item = hello.getResponse().getBody().getItems().getItem().get(3);
            System.out.println(item.getObsrValue());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
