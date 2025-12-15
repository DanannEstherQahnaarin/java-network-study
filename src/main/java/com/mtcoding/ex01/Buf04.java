package com.mtcoding.ex01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Buf04 {
    public static void main(String[] args) {
        OutputStream out = System.out;
        OutputStreamWriter ow = new OutputStreamWriter(out);
        BufferedWriter bw = new BufferedWriter(ow);

        try {
            bw.write("ABC");
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
