package com.mtcoding.ex01;

import java.io.IOException;
import java.io.InputStream;

public class Buf01 {
    public static void main(String[] args) {
        //keyboard와 computer가 byte 스트리밍 연결됨
        InputStream in = System.in;

        //바이트 읽기
        try {
            int n = in.read();// keyboard입력 대기
            System.out.println(n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
