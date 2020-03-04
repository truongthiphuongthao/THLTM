/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Bai3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
class ReadFileThread extends Thread{
    Socket socket;
//    File file;
    public ReadFileThread (Socket socket){
        this.socket=socket;
    }
   // Doc file
    private static byte[] readFile(String tenFile){
        File file = new File(tenFile);
        int lenFile = (int)file.length();
        byte b_lenFile[] = new byte[lenFile];
        try{
            FileInputStream fis = new FileInputStream(file);
            fis.read(b_lenFile);
            fis.close();
        }catch(IOException e){
            System.out.println(e.toString());
        }
        return b_lenFile;
    }
    
    public void run(){
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            Scanner sc = new Scanner(is);
            PrintWriter pw = new PrintWriter(os);
            String request = sc.nextLine();
            if(request.equals("q")) return;
            String[] params = request.split(" ");
            String fileToRead = params[1];                    
            byte[] b_bytes = readFile(fileToRead);
            String filebyte = new String(b_bytes,0,b_bytes.length);
            System.out.println(filebyte);
            os.write(b_bytes);
            socket.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
    
 class ReadFileServer {
    public static void main(String[] args) {
        // TODO code application logic here
        try{
        ServerSocket serverSocket = new ServerSocket(8998);
        System.out.println("Server dang khoi tao");
        while(true){
            try{ 
                // Chap nhan noi ket (Dong bo) 
                Socket clientSocket = serverSocket.accept();
                System.out.println ("Nhan 1 ket noi tu " + clientSocket);       
                ReadFileThread readFileThread = new ReadFileThread(clientSocket);
                readFileThread.start();
            
            }catch(IOException e){
                System.out.println("Co loi khi noi ket client "+e.toString());
            }
        }
      }catch(IOException e){
          System.out.println("Khong khoi tao duoc server "+e.toString());
      }
      catch(NoSuchElementException e){
          System.out.println(e.toString());
      }
    }
}


