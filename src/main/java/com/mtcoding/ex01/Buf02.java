package com.mtcoding.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Buf02 {
    public static void main(String[] args) {
        // stream 연결
        InputStream in = System.in;

        // 배열을 가지고 문자로 캐스팅
        InputStreamReader ir = new InputStreamReader(in);
        char[] buffer = new char[3];
        try {
            ir.read(buffer);

            for (char c : buffer) {
                System.out.print(c + ", ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
