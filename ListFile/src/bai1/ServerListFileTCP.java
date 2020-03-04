/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ServerListFileTCP {
   private final static int congTCP = 2001;
   private final static String dcserver ="127.0.0.1";
    
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            ServerSocket serverSocket = new ServerSocket(congTCP);
            while(true){
                    Socket clientSocket = serverSocket.accept();
                    ThreadList threadList = new ThreadList(clientSocket);
                    threadList.start();
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }
}
 class ThreadList extends Thread{
    Socket tcpSocket;
    public ThreadList(Socket tcpSocket){
        this.tcpSocket = tcpSocket;
    }
    public void run(){
        try{
            InputStream is = tcpSocket.getInputStream();
            OutputStream os = tcpSocket.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            Scanner kb = new Scanner(System.in);
            // Server nhan danh sach cac file tu client
            String recv = sc.nextLine();
            String params[] = recv.split(" ");
            String tenThuMuc = params[1];
            File file = new File(tenThuMuc);
            if(file.exists() && file.isDirectory()){
                String arrFile[] = file.list();
                int n_arrFile = arrFile.length;
                pw.println(n_arrFile+"");
                pw.flush();
                for(int i=0;i<n_arrFile;i++){
                    System.out.println(arrFile[i]);
                    // vidu.txt
                    pw.println(arrFile[i]);
                    pw.flush();
                }
            }
            else{
                String strFile = "-1";
                pw.println(strFile);
                pw.flush();
                
            }
            tcpSocket.close();
            
        }catch(IOException e){
            System.out.println(e.toString());
        }
    }
}
