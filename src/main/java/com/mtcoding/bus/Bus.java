package com.mtcoding.bus;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Bus {
    private Response response;

    @Getter
    @Setter
    public static class Response{
        private Header header;
        private Body body;

        @Getter
        @Setter
        public static class Header{
            private String resultCode;
            private String resultMsg;

        }

        @Getter
        @Setter
        public static class Body{
            private String dataType;
            private Items items;
            private Integer PageNo;
            private Integer numOfRows;
            private Integer totalCount;

            @Getter
            @Setter
            public static class Items{
                private List<Item> item;

                @Getter
                @Setter
                static class Item {
                    private String arsno;
                    private String bstopid;
                    private String lineno;
                    private String nodenm;
                    private String bstopidx;
                    private String carno1;
                    private String min1;
                    private String station1;
                    private String lowplate1;
                    private String seat1;
                    private String carno2;
                    private String min2;
                    private String station2;
                    private String lowplate2;
                    private String seat2;
                    private String gpsx;
                    private String gpsy;
                    private String bustype;
                    private String lineid;

                }
            }
        }
    }
}
