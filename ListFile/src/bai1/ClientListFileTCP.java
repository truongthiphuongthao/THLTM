/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClientListFileTCP {
    private final static int congTCP = 2001;
    private final static String dcserver = "127.0.0.1";
    
    public static void main(String[] args) {
        // TODO code application logic here
        // Noi ket Socket 
        try{
            Socket clientSocket = new Socket(dcserver,congTCP);
            InputStream is = clientSocket.getInputStream();
            OutputStream os = clientSocket.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            Scanner kb = new Scanner(System.in);
            // Nhap ten thu muc
            System.out.println("Thu muc:");
            String tenThuMuc = kb.nextLine();
            String send ="LIST "+tenThuMuc;
            pw.println(send);
            pw.flush();
            // Server gui danh sach cac file
            String recvFile = sc.nextLine();
            int n_recvFile = Integer.parseInt(recvFile);
            if(n_recvFile==-1){
                System.out.println("Thu muc khong ton tai");
            } else{
                System.out.println("Noi dung thu muc");
                for(int i=0;i<n_recvFile;i++){
                    String recvFileChild = sc.nextLine();
                    System.out.println(recvFileChild);
                }
            }
            clientSocket.close();
            
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }
}
