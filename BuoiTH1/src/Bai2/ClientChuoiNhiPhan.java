/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClientChuoiNhiPhan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{  // Tao noi ket socket 
            Socket clientSocket = new Socket("127.0.0.1",8998);
            // Tao 2 luong nhap xuat
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            Scanner kb = new Scanner(System.in);
            // Nhap chuoi so nguyen
            while(true){
                System.out.println("Nhap chuoi so nguyen:");
                String chuoi = kb.nextLine();
                if(chuoi.equals("q")) break;
                // Chuyen chuoi thanh byte
                byte b_chuoi[] = chuoi.getBytes();
                os.write(b_chuoi);
                // Nhan chuoi tu server goi ve
               byte b_result[] = new byte[100];
               int n = is.read(b_result);
               String result = new String(b_result,0,n);
               System.out.println("Ket qua:"+result);
            } 
            clientSocket.close();
        }catch(IOException e){
            System.out.println("Loi noi ket tu client den server"+e.toString());
        }
    }
}
