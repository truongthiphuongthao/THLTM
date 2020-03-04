/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Dell
 */
public class ServerReadUDP {
    private final static int congUDP = 2001;
    private final static String dcserver="127.0.0.1";
    
    private static byte[] readFile(String tenFile){
        File file = new File(tenFile);
        int lenFile = (int)file.length();
        byte b_file[] = new byte[lenFile];
        try{    
             FileInputStream fis = new FileInputStream(file);
             fis.read(b_file);
             fis.close();
        }catch(IOException e){
            System.out.println(e.toString());
        }
        return b_file;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            DatagramSocket udpSocket = new DatagramSocket(congUDP);
           while(true){
               byte b_recv[] = new byte[60000];
               int n_recv = b_recv.length;
               DatagramPacket goinhan = new DatagramPacket(b_recv,0,n_recv);
               udpSocket.receive(goinhan);
               String recv = new String(goinhan.getData(),0,goinhan.getLength());
               String params[] = recv.split(" ");
               String tenFile = params[1];
               byte b_docFile[] = readFile(tenFile);
               DatagramPacket goigui = new DatagramPacket(b_docFile,b_docFile.length,goinhan.getAddress(),goinhan.getPort());
               udpSocket.send(goigui);
//               udpSocket.close();
           }       
        }catch(IOException e){
            System.out.println("Khong khoi tao duoc server"+e.toString());
        }
    }
    
}
