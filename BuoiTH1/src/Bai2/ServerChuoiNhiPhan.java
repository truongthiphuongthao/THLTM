/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bai2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dell
 */
public class ServerChuoiNhiPhan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Tao noi ket
        try{
            ServerSocket serverSocket = new ServerSocket(8998);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Co 1 noi ket:"+clientSocket.getInetAddress()+"Cong:"+clientSocket.getPort());
            // Tao hai stream nhap xuat
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            while(true){
                // Tao 1 mang byte de nhan vao
                byte b_in[] = new byte[100];
                int n_in =is.read(b_in);
                String str_in = new String(b_in,0,n_in);
                if(str_in.equals("q")) break;
                String binaryStr = "";
                // Doi chuoi thanh so
                try{
                    int intBinary = Integer.parseInt(str_in);
                    binaryStr = Integer.toBinaryString(intBinary);
                }catch(NumberFormatException ie){
                    binaryStr="Khong phai so nguyen";
                }
                os.write(binaryStr.getBytes());     
            }
            clientSocket.close();
            System.out.println("Dong socket "+clientSocket.getPort());
    }catch(IOException e ){
        System.out.println("Loi noi ket tu server"+e.toString());
    }
    }
}
