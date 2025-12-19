package com.mtcoding.bus;

import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class BusApp {
    public static void main(String[] args) {
        String xmlBustopUrl = "https://apis.data.go.kr/6260000/BusanBIMS/busStopList?serviceKey=0bd6eafcaec213c796db7445926cc744555fe4e8d27f7a425b8cccd0e92877fc&pageNo=1&numOfRows=9999";
        String bstopJson = getJsonData(xmlBustopUrl);
        Bstop bstop = new Gson().fromJson(bstopJson, Bstop.class);

        Scanner bstopScanner = new Scanner(System.in);

        HashSet<String> bstopSet = new HashSet<>();
        List<Bstop.Response.Body.Items.Item> list = bstop.getResponse().getBody().getItems().getItem();

        for(Bstop.Response.Body.Items.Item item : list){
            bstopSet.add(item.getBstopnm());
        }

        System.out.println("정류장명을 입력하세요");
        System.out.println();

        for (String str : bstopSet){
            System.out.print(str + ",");
        }

        System.out.println();

        String selSetName = bstopScanner.nextLine();
        List<Bstop.Response.Body.Items.Item> selBstopList = new ArrayList<>();

        for (Bstop.Response.Body.Items.Item item : list) {
            if(item.getBstopnm().equals(selSetName)){
                selBstopList.add(item);
            }
        }

        System.out.println("정류장 번호를 입력하세요");

        for (int i=0; i < selBstopList.size(); i++){
            Bstop.Response.Body.Items.Item item = selBstopList.get(i);
            System.out.println("번호" + (i+ 1) + " : " +  item.getBstopnm());
        }

        String selNum = new Scanner(System.in).nextLine();

        Bstop.Response.Body.Items.Item item = selBstopList.get(Integer.parseInt(selNum));

        String xmlBusUrl = """
                https://apis.data.go.kr/6260000/BusanBIMS/stopArrByBstopid?serviceKey=0bd6eafcaec213c796db7445926cc744555fe4e8d27f7a425b8cccd0e92877fc&stdt=${bstopid}
                """.replace("${bstopid}",item.getBstopid());

        String busJson = getJsonData(xmlBusUrl);

        Bus bus = new Gson().fromJson(busJson, Bus.class);
        System.out.println();

        List<Bus.Response.Body.Items.Item> busList = bus.getResponse().getBody().getItems().getItem();

        System.out.println("버스번호를 입력하세요");
        for (Bus.Response.Body.Items.Item busItem : busList){
            System.out.println("버스번호 : " + busItem.getCarno1() + " 도착시간 : " + busItem.getMin1() + "분 뒤 " + "남은역 : " + busItem.getStation1());
        }

        String busNo = new Scanner(System.in).nextLine();
        System.out.println(busNo);

        Bus.Response.Body.Items.Item busItem = null;

        for (int i = 0; i < busList.size(); i++) {
            if(busNo.equals(busList.get(i).getCarno1())){
                busItem = busList.get(i);
                break;
            }
        }

        String xmlLineUrl = """
                https://apis.data.go.kr/6260000/BusanBIMS/busInfoByRouteId?serviceKey=0bd6eafcaec213c796db7445926cc744555fe4e8d27f7a425b8cccd0e92877fc&lineid=${lineid}
                """.replace("${lineid}",busItem.getLineid());

        String lineJson = getJsonData(xmlLineUrl);

        Line busLine = new Gson().fromJson(lineJson, Line.class);

        System.out.println();
        System.out.println("버스노선");

        List<Line.Response.Body.Items.Item> lineList = busLine.getResponse().getBody().getItems().getItem();
        for(Line.Response.Body.Items.Item lineItem : lineList){
            System.out.println(lineItem.getBstopnm());
        }
    }


    static String getJsonData(String jsonUrl){
        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");

            Scanner sc = new Scanner(conn.getInputStream());
            String xml = "";

            while (sc.hasNextLine()){
                String line = sc.nextLine();
                xml= xml + line;
            }

            JSONObject jsonObject = XML.toJSONObject(xml);

            return jsonObject.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
