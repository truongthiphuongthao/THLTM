/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
/**
 *
 * @author Dell
 */
public class ServerTalk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Chat voi client");
        try{
            DatagramSocket ds  = new DatagramSocket(8998);
            // Tao vung luu goi nhan
            byte b_receiveB[] = new byte[60000];
            int n_receiveB = b_receiveB.length;
            Scanner kb = new Scanner(System.in);
            while(true){
                 // Tao goi nhan
                DatagramPacket goinhan = new DatagramPacket(b_receiveB,n_receiveB);
                ds.receive(goinhan);
                // Lay thong tin goi nhan 
                byte b_result[] = goinhan.getData();
                int n_result = goinhan.getLength();
                String result = new String(b_result,0, n_result);
                System.out.println("May A:"+result);
                System.out.print("May B:");
                String sendB = kb.nextLine();
                if(sendB.equals("q")) break;
                // Thong tin goi gui
                byte b_sendB[] = sendB.getBytes();
                int n_sendB = b_sendB.length;
                int cong = goinhan.getPort();
                InetAddress dc = goinhan.getAddress();
                // Tao goi gui
                DatagramPacket goigui = new DatagramPacket(b_sendB,n_sendB,dc,cong);
                ds.send(goigui);
            }
        }catch(IOException e){
            System.out.println("Khong khoi tao duoc server"+e.toString());
        }
    }
}
