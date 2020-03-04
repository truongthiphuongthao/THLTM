/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClientDocSo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Tao noi ket socket
        try{
        Socket clientSocket = new Socket ("127.0.0.1",7);
        // Tao 2 stream nhap xuat
        InputStream is = clientSocket.getInputStream();
        OutputStream os = clientSocket.getOutputStream();
        Scanner kb = new Scanner(System.in);
        // Nhap 1 ki tu tu ban phim 
        while(true){
            System.out.println("Nhap ki tu tu ban phim:");
            // Nhap 1 ki tu tu ban phim
            int ch=kb.nextLine().charAt(0);
            // Gui ki tu do qua server
            if(ch=='q') break; 
             // Dieu kien de thoat
            os.write(ch);
            // Tao 1 mang byte de nhan ki tu ve
            byte b_result[] = new byte[100];
            int n = is.read(b_result);
            String str_result = new String(b_result,0,n);
            System.out.println("Ket qua la:"+str_result);
        }
    }catch(IOException e){
        System.out.println("Loi noi ket tu client den server"+e.toString());
    }
    }
}
