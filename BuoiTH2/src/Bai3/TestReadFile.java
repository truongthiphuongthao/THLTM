/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Dell
 */
public class TestReadFile {
    private static String readFile(File file){
         String lineadd="";
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";
        while((line = br.readLine())!=null){
            lineadd+=line;
        }
        }catch(IOException e){
            System.out.println( e.toString());
        }
       return lineadd;
    }
    
    public static void main (String[] args) {
        System.out.println (readFile (new File ("F:/vidu.txt")));
    }
}
