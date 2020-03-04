/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Bai2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ListFileClient {
    
    public static void main(String[] args) {
        // Tao noi ket socket
        try{
            Socket clientSocket = new Socket("127.0.0.1",8998);
            // Tao hai luong nhap xuat
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            // Nhap ten thu muc
            Scanner kb = new Scanner(System.in);
            while(true){
                System.out.println("Nhap ten thu muc:");
                String nameDir = kb.nextLine();
                if(nameDir.equals("q")) break;
                pw.println("LIST "+nameDir); // 
                pw.flush();
                // Nhan ket qua va hien thi
                String result1 = sc.nextLine();
                int n = Integer.parseInt(result1);
                if(n==-1){
                    System.out.println("Thu muc khong ton tai");
                } else if (n==-2) {
                    System.out.println("Phuong thuc khong ho tro!");
                } else{
                    System.out.println("Noi dung thu muc");
                    for(int i=0;i<n;i++){
                        String result2 = sc.nextLine();
                        System.out.println(result2);
                    }
                }
            }
            clientSocket.close();
        }catch(IOException e){
            System.out.println("Loi noi ket tu client"+e.toString());
        }
    }
    
}
