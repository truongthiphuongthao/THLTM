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
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Dell
 */
public class ServerDateTime {
    private final static int congUDP=13;
    private final static String dcserver="127.0.0.1";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            DatagramSocket udpSocket = new DatagramSocket(congUDP);
            while(true){
                // Nhan goi nhan
                byte b_recv[] = new byte[60000];
                int n_recv = b_recv.length;
                DatagramPacket goinhan = new DatagramPacket(b_recv,n_recv);
                udpSocket.receive(goinhan);
                // Xu li Date Time
                Date d = new Date();
                String send_d = d.toString();
                // Gui goi gui hien thi thoi gian he thong
                DatagramPacket goigui = new DatagramPacket(send_d.getBytes(),send_d.length(),goinhan.getAddress(),goinhan.getPort());
                udpSocket.send(goigui);       
          }        
        }catch(IOException e){
            System.out.println("Khong khoi tao duoc server"+e.toString());
        }
    }
}
