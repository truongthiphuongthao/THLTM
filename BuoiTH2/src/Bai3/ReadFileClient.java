/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Bai3;

import java.io.File;
import java.io.FileOutputStream;
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
public class ReadFileClient {
    public static void main(String[] args) {
        // TODO code application logic here
        // Tao noi ket socket
        try{  
            while(true){
                Socket socket = new Socket("127.0.0.1", 8998);
                // Tao hai luong nhap xuat
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                Scanner sc = new Scanner(is);
                PrintWriter pw = new PrintWriter(os);
                Scanner kb = new Scanner(System.in);
                System.out.println("Nhap ten tep tin: ");
                String tenthumuc = kb.nextLine();
                  // Dieu kien de thoat
                if(tenthumuc.equals("q")) break;
                pw.println("READ "+tenthumuc);
                pw.flush();
                 // Nhan ket qua hien tai va hien thi
                byte[] result = new byte[640000];
                int n_result = is.read(result);
                String str_result = new String(result,0,n_result);
                System.out.println(str_result); 
                
                System.out.println("Ten file can luu:");
                String tenFileLuu = kb.nextLine();
                FileOutputStream fos = new FileOutputStream(tenFileLuu);
                fos.write(result,0,n_result);
                fos.close();
                System.out.println("Da luu file thanh cong");
                socket.close();
             }
        } catch(IOException e){
            System.out.println("Loi noi ket tu client");
        }
        
    }
}
