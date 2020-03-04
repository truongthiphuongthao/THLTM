/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package bai1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ClientDateTime {
  private final static  int congUDP =13;
  private final static String dcserver ="127.0.0.1";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
           
            Scanner kb = new Scanner(System.in);
            
            while(true){
                DatagramSocket udpSocket = new DatagramSocket();
                // Gui goi gui
                System.out.println("Gui goi tin rong:");
                String send = kb.nextLine();
                InetAddress dc = InetAddress.getByName(dcserver);
                DatagramPacket goigui = new DatagramPacket(send.getBytes(),send.length(),dc,congUDP);
                udpSocket.send(goigui);
                // Nhan goi nhan
                byte b_recv[] = new byte[60000];
                int n_recv = b_recv.length;
                DatagramPacket goinhan = new DatagramPacket(b_recv,0,n_recv);
                udpSocket.receive(goinhan);
                // Lay noi dung goi nhan hien thi len man hinh
                String recv = new String(goinhan.getData(),0,goinhan.getLength());
                System.out.println("Ket qua:"+recv);
                udpSocket.close();
            }
        }catch(IOException e){
            System.out.println(e.toString());
        }
        
    }
    
}
