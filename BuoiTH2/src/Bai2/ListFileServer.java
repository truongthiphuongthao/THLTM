/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Bai2;

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
class ListFileThread extends Thread{
    Socket socket;
    public ListFileThread(Socket socket){
        this.socket = socket;
    }
    public void run(){
        // Tao ha luong nhap xuat
        try{
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
        while(true){
             //Nhan yeu cau tu client
            String request = sc.nextLine();
            // Kiem tra dieu kien de thoat
            if(request.equals("q")) break;
            // List thu muc va gui cho client
            String[] params = request.split (" "); //      
            if (!params[0].equals("LIST")) {
                String str ="-2";
                pw.println(str);
                pw.flush();
                break;                              
            }
            String nxtToGet = "."; // 
            if (params.length > 1) {
                nxtToGet = params[1];
            }     
           String nameDir ="f:\\"+nxtToGet;
           System.out.println("Se gui đi cac thu muc trong duong dan:"+nameDir);
           File file = new File(nameDir);
            if(file.exists() && file.isDirectory()){
                 String arr_listFile[]= file.list();    
                 int n = arr_listFile.length;
                 pw.println(n+"");
                 for(int i=0;i<n;i++){
                     File subfile = new File (nameDir+"\\"+arr_listFile[i]);
                     if (subfile.isDirectory())
                        pw.println("|__ "+arr_listFile[i]);                     
                     else 
                        pw.println(arr_listFile[i]);
                 }
                 pw.flush();
            }
            else{
                String str ="-1";
                pw.println(str);
                pw.flush();
            }
        }
         socket.close();
        }catch(IOException e){
            System.out.println("Co loi khi noi ket client "+e.toString());
        }
    }
}
 class ListFileServer {
    
    public static void main(String[] args) {
      try{
        ServerSocket serverSocket = new ServerSocket(8998);
        System.out.println("Server dang khoi tao");
        while(true){
            try{
            Socket clientSocket = serverSocket.accept();
            ListFileThread listFileThread = new ListFileThread(clientSocket);
            listFileThread.start();
            }catch(IOException e){
                System.out.println("Co loi khi noi ket client "+e.toString());
            }
        }
      }catch(IOException e){
          System.out.println("Khong khoi tao duoc server "+e.toString());
      }
       
    }
}
