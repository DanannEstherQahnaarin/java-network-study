package com.mtcoding.ex10;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class Hello {
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
                    private String baseDate;
                    private String baseTime;
                    private String category;
                    private Double nx;
                    private Double ny;
                    private String obsrValue;

                }
            }
        }
    }
}
