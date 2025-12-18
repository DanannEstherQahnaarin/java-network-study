package com.mtcoding.airplane;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AirApp {
    /*
    * 1. 공항정보 다운
    * 2. PortInfo 오브젝트화
    * 3. Map에  nm - > key, id -> value
    *
    * 4. 스캐너로 출발지 받기 String depKey=""
    * 5. 스캐너로 목적지 받기 String arrKey=""
    * 6. 스캐너로 날짜 받기
    *
    * 7. 동적 URL 만들기 String url
    * 8. 항공스케줄 httpUrlConnection 호출
    * 9. AirInfo 오브젝트화
    * 10. 항공스케줄 전체 출력
    * */
    public static void main(String[] args) {
        String deptKey = "";
        String arrKey = "";
        String date = "";
        String portsUrl = "https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getArprtList?serviceKey=0bd6eafcaec213c796db7445926cc744555fe4e8d27f7a425b8cccd0e92877fc&_type=json";
        String portJson = getJsonData(portsUrl);
        PortInfo portInfo = new Gson().fromJson(portJson, PortInfo.class);

        HashMap<String,String> portMap = new HashMap<>();
        HashMap<String,AirInfo> scheduleMap = new HashMap<>();

        List<PortInfo.Response.Body.Items.Item> portList = portInfo.getResponse().getBody().getItems().getItem();
        for (PortInfo.Response.Body.Items.Item item : portList){
            portMap.put(item.getAirportNm(),item.getAirportId());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("====================");

        for(String key : portMap.keySet()){
            System.out.print(key + ",");
        }
        System.out.println("출발지를 입력하여 주십시오.");
        String inputDept = scanner.nextLine();
        deptKey = portMap.get(inputDept);


        if(deptKey == null) return;

        System.out.println("====================");

        for(String key : portMap.keySet()){
            System.out.print(key + ",");
        }

        System.out.println("도착지를 입력하여 주십시오.");
        String inputArr = scanner.nextLine();
        arrKey = portMap.get(inputArr);

        if(arrKey == null) return;

        System.out.println("날짜를 입력하여 주십시오(YYYYMMDD).");
        date = scanner.nextLine();

        String scheduleUrl = """
                https://apis.data.go.kr/1613000/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=0bd6eafcaec213c796db7445926cc744555fe4e8d27f7a425b8cccd0e92877fc&pageNo=1&numOfRows=10&_type=json&depAirportId=${arrKey}&arrAirportId=${deptKey}&depPlandTime=${date}
                """.replace("${arrKey}", arrKey).replace("${deptKey}", deptKey).replace("${date}",date);

        //System.out.println(scheduleUrl);

        String airInfoJson = getJsonData(scheduleUrl);

        //System.out.println(airInfoJson);

        AirInfo airInfo = new Gson().fromJson(airInfoJson, AirInfo.class);

        List<AirInfo.Response.Body.Items.Item> airInfoList = airInfo.getResponse().getBody().getItems().getItem();

        for (AirInfo.Response.Body.Items.Item item : airInfoList){
            System.out.println("-------------------------");
            System.out.println("AirlineNm : " + item.getAirlineNm());
            System.out.println("AirportNm : " + item.getArrAirportNm());
            System.out.println("ArrPlandTime : " + item.getArrPlandTime());
            System.out.println("DepAirportNm : " + item.getDepAirportNm());
            System.out.println("DepPlandTime : " + item.getDepPlandTime());
            System.out.println("EconomyCharge : " + item.getEconomyCharge());
            System.out.println("PrestigeCharge : " + item.getPrestigeCharge());
            System.out.println("VihicleId : " + item.getVihicleId());
        }
    }

    static String getJsonData(String jsonUrl){
        try {
            URL url = new URL(jsonUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");

            Scanner sc = new Scanner(conn.getInputStream());
            String json = "";

            while (sc.hasNextLine()){
                String line = sc.nextLine();
                json= json + line;
            }

            return json;
        } catch (Exception e) {
            return "";
        }
    }
}
