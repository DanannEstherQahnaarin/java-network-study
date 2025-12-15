package com.mtcoding.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Buf03 {
    public static void main(String[] args) {
        InputStream in = System.in;
        InputStreamReader ir = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(ir);

        try {
            String line = br.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
