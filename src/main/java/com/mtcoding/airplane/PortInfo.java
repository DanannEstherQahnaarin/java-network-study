package com.mtcoding.airplane;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class PortInfo {
    private Response response;

    @Getter
    @Setter
    public static class Response{
        private Response.Header header;
        private Response.Body body;

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
            private Response.Body.Items items;
            private Integer PageNo;
            private Integer numOfRows;
            private Integer totalCount;

            @Getter
            @Setter
            public static class Items{
                private List<Response.Body.Items.Item> item;

                @Getter
                @Setter
                static class Item {
                    private String airportId;
                    private String airportNm;
                }
            }
        }
    }
}
