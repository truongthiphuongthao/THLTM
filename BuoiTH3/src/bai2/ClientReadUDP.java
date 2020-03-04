/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai2;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
/**
 *
 * @author Dell
 */
public class ClientReadUDP {
    private final static int congUDP = 2001;
    private final static String dcserver ="127.0.0.1";
    // Kiem tra file
    private static boolean checkFile(String tenFile){
        boolean check = false;
        File file = new File(tenFile);
        int maxlength = 64 *1024;
        int lenfile = (int)file.length();
        if(lenfile > maxlength){
            check = true;
            System.out.println("File qua lon");
        }
        if(!file.exists()){
            check = true;
            System.out.println("File khong ton tai");
        }
        return check;
    }
    public static void main(String[] args) {
        // TODO code application logic here
        try{
           DatagramSocket udpSocket = new DatagramSocket();
           Scanner kb = new Scanner(System.in);
           String tenFile = "";
           do{
                System.out.println("Ten file:");
                tenFile = kb.nextLine();
           }while(checkFile(tenFile));
           InetAddress dc = InetAddress.getByName(dcserver);
           String send_tenFile = "READUDP "+tenFile;
           DatagramPacket goigui = new DatagramPacket(send_tenFile.getBytes(),send_tenFile.length(),dc,congUDP);
           udpSocket.send(goigui);
           // Nhan goi nhan
           byte b_recv[] = new byte[60000];
           int n_recv = b_recv.length;
           DatagramPacket goinhan = new DatagramPacket(b_recv,0,n_recv);
           udpSocket.receive(goinhan);
           // Luu file
           System.out.println("Ten file can luu:");
           String tenFileLuu = kb.nextLine();
           FileOutputStream fos = new FileOutputStream(tenFileLuu);
           fos.write(goinhan.getData(),0,goinhan.getLength());
           System.out.println("Da luu File thanh cong");
        }catch(IOException e){
            System.out.println("Khong noi ket duoc voi server"+e.toString());
        }
    } 
}
